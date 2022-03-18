import express from 'express'
import { WebAccountController } from '../../controllers/webAccountController.js'
import { JWTService } from '../../services/jwtService.js'

export const router = express.Router()

const controller = new WebAccountController()
const jwtService = new JWTService()

// POST
router.post('/register', (req, res, next) => controller.register(req, res, next))
router.post('/login', (req, res, next) => controller.login(req, res, next))
router.get('/welcome', (req, res) => res.json({ message: 'Welcome to web app' }))

// DELETE
router.delete('/user',
  (req, res, next) => jwtService.authenticateJWT(req, res, next),
  (req, res, next) => controller.delete(req, res, next)
)