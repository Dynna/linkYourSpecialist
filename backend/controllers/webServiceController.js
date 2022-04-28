import { PostModel } from '../models/servicePostModel.js'
import dotenv from 'dotenv'
dotenv.config();

export class WebServiceController {
    async index (req, res) {
        try {
            const posts = await PostModel.find({
                where: {}
            })
            res
                .status(200)
                .json(posts)
        } catch (error) {
            next(error)
        }
    }
}