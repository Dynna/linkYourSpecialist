import express from 'express'
import createError from 'http-errors'
import { router as apiRouter } from './api/router.js'

export const router = express.Router()

router.get('/', (req, res) => res.json({ message: 'Welcome to Link Your Specialist API' }))

router.use('/api', apiRouter)

router.use('*', (req, res, next) => next(createError(404)))
