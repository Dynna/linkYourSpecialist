import { PostModel } from '../models/servicePostModel.js'
import { MobileAccount } from '../models/mobileUserModel.js'
import { AvailabilityModel } from '../models/specialistAvailabilityModel.js'
import dotenv from 'dotenv'
dotenv.config();

export class WebServiceController {
    async index (req, res, next) {
        try {
            let posts = null;
            const search = req.query.search
            if (search) {
                posts = await PostModel.find({
                    $or: [ {name: {$regex: search, $options: 'i'}},
                           {category: {$regex: search, $options: 'i'}}
                    ]
                    // where: {
                    //     $or: [
                    //         'name', 'category'
                    //     ].map(key = ({
                    //         [key]: {
                    //             $like: `%${search}%`
                    //         }
                    //     }))
                    // }
                })
            } else {
                posts = await PostModel.find({
                    where: {}
                })
            }
            res
                .status(200)
                .json(posts)
        } catch (error) {
            next(error)
        }
    }

    async show (req, res, next) {
        try {
            const post = await PostModel.findById(req.params.serviceId)
            const specialistInfo = await MobileAccount.findOne({ _id: req.params.specialistId }) 
            const availability = await AvailabilityModel.find({ userID: req.params.specialistId })
            res
                .status(200)
                .json({ post, specialistInfo, availability })
        } catch (error) {
            next(error)
        }
    }
}