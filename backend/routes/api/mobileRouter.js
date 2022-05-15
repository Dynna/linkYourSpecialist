import express from 'express'
import { MobileAccountController } from '../../controllers/mobileAccountController.js'
import { JWTService } from '../../services/jwtService.js'

export const router = express.Router()

const controller = new MobileAccountController()
const jwtService = new JWTService()

// authentication
router.post('/register', (req, res, next) => controller.register(req, res, next))
router.post('/login', (req, res, next) => controller.login(req, res, next))

// DELETE
router.delete('/user',
  (req, res, next) => jwtService.authenticateJWT(req, res, next),
  (req, res, next) => controller.delete(req, res, next)
)

//USER POSTS
router.post('/newPost',
  (req, res, next) => jwtService.authenticateJWT(req, res, next),
  (req, res, next) => controller.createPost(req, res, next)
)

//when the request will be created we need to add a header with userid
router.get('/posts',
  (req, res, next) => jwtService.authenticateJWT(req, res, next),
  (req, res, next) => controller.getPosts(req, res, next)
)

// this route need header with userid and authorization token
router.post('/updateUser',
  (req, res, next) => jwtService.authenticateJWT(req, res, next),
  (req, res, next) => controller.updateUser(req, res, next)
)

//availability data
router.post('/availability/newItem',
  (req, res, next) => jwtService.authenticateJWT(req, res, next),
  (req, res, next) => controller.insertAvailability(req, res, next)
)

router.get('/availability/getItem',
  (req, res, next) => jwtService.authenticateJWT(req, res, next),
  (req, res, next) => controller.getAvailabilityItem(req, res, next)
)

router.post('/newRequest',
  (req, res, next) => controller.insertBookRequest(req, res, next)
)

router.get('/bookRequests',
  (req, res, next) => jwtService.authenticateJWT(req, res, next),
  (req, res, next) => controller.getBookRequest(req, res, next)
)