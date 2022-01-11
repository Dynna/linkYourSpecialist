import express from 'express'
import { router as mobileRouter } from './mobileRouter.js'

export const router = express.Router()

router.use('/mobileUsers', mobileRouter)