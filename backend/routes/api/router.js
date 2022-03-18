import express from 'express'
import { router as mobileRouter } from './mobileRouter.js'
import { web_router as webRouter } from './webRouter.js'

export const router = express.Router()
export const web_router = express.Router()

router.use('/mobileUsers', mobileRouter)
web_router.use('/webUsers', webRouter)