import express from 'express'
import { router as mobileRouter } from './mobileRouter.js'
import { router as webRouter } from './webRouter.js'

export const router = express.Router()

router.use('/mobileUsers', mobileRouter)
router.use('/web', webRouter)