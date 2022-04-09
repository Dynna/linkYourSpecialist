import express from 'express'
import { MobileAccountController } from '../../controllers/mobileAccountController.js'
import { JWTService } from '../../services/jwtService.js'

export const router = express.Router()

const controller = new MobileAccountController()
const jwtService = new JWTService()

// POST
router.post('/register', (req, res, next) => controller.register(req, res, next))
router.post('/login', (req, res, next) => controller.login(req, res, next))

// DELETE
router.delete('/user',
  (req, res, next) => jwtService.authenticateJWT(req, res, next),
  (req, res, next) => controller.delete(req, res, next)
)

//USER POSTS
router.post('/newPost',
  //(req, res, next) => jwtService.authenticateJWT(req, res, next),
  (req, res, next) => controller.createPost(req, res, next)
)

//when the request will be created we need to add a header with userid
router.get('/posts',
  //(req, res, next) => jwtService.authenticateJWT(req, res, next),
  (req, res, next) => controller.getPosts(req, res, next)
)