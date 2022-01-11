import fs from 'fs/promises'
import jwt from 'jsonwebtoken'
import createError from 'http-errors'
import { MobileAccount } from '../models/mobileUserModel.js'
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
                expiresIn: Number(process.env.ACCESS_TOKEN_LIFE)
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
                contactInfo: req.body.contactInfo,
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
}