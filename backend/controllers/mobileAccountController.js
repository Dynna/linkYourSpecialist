import fs from 'fs/promises'
import jwt from 'jsonwebtoken'
import createError from 'http-errors'
import { MobileAccount } from '../models/mobileUserModel.js'
import { PostModel } from '../models/servicePostModel.js'
import dotenv from 'dotenv'
dotenv.config();
export class MobileAccountController {

    async login(req, res, next) {
        try {
            const account = await MobileAccount.authenticate(req.body.email, req.body.password)

            // Create a JWT.
            const payload = {
                sub: account.id,
                name: account.username
            }
            const secretKey = process.env.TOKEN_SECRET_KEY
            const accessToken = jwt.sign(payload, secretKey, {
                expiresIn: '500d'
            })
            res
                .status(200)
                .json({
                    user: account,
                    access_token: accessToken
                })
        } catch (error) {
            let err = error
            if (err.name === 'credentialsError') {
                err = createError(401)
                err.innerException = error
            }

            next(err)
        }
    }

    async register(req, res, next) {
        try {
            const account = await MobileAccount.insert({
                username: req.body.username,
                email: req.body.email,
                name: req.body.name,
                surname: req.body.surname,
                birthday: req.body.birthday,
                categories: req.body.categories,
                experience: req.body.experience,
                phone: req.body.phone,
                location: req.body.location,
                password: req.body.password
            })
            res
                .status(201)
                .json(account)
        } catch (error) {
            let err = error

            // Dublicate keys error.
            if (err.code === 11000) {
                let message = ''

                if (err.keyPattern.email) {
                    message = 'The email is already taken'
                } else if (err.keyPattern.username) {
                    message = 'The username is already taken'
                }

                err = createError(409, message)
                err.innerException = error
            } else if (error.name === 'ValidationError') {
                err = createError(400)
                err.innerException = error
            }

            next(err)
        }
    }


    async delete(req, res, next) {
        try {
            const response = await MobileAccount.deleteOne({ _id: req.account.userID })
            if (response.deletedCount === 0) {
                next(createError(404))
                return
            }
            res
                .status(204)
                .end()
        } catch (error) {
            next(error)
        }
    }

    async createPost(req, res, next) {
        try {
            const message = {message: "successful"}
            const post = await PostModel.insert({
                userID: req.body.userID,
                category: req.body.category,
                name: req.body.name,
                description: req.body.description,
                serveAtHome: req.body.serveAtHome
            })
            res
                .status(201)
                .json(message)
        } catch (error) {
            next(error)
        }
    }


    async getPosts(req, res, next) {
        try {
            const id = req.headers['userid']
            const posts = await PostModel.find({
                userID: id
            })
            res
                .status(200)
                .json(posts)
        } catch (error) {
            next(error)
        }
    }
}