import createError from 'http-errors'
import fs from 'fs/promises'
import jwt from 'jsonwebtoken'
import dotenv from 'dotenv'
dotenv.config();

export class JWTService {
  /**
   * Authenticates requests.
   *
   * If authentication is successful, `req.account` is populated and the
   * request is authorized to continue.
   * If authentication fails, an unauthorized response will be sent.
   */
  async authenticateJWT(req, res, next) {
    try {
      const authorization = req.headers.authorization?.split(' ')
      //console.log(authorization[0])

      if (authorization[0] !== 'Bearer') {
        next(createError(401))
        //console.log("bearer error")
        return
      }
      else {
        //console.log("1111")
        const secretKey = process.env.TOKEN_SECRET_KEY
        console.log(authorization[1])
        const payload = jwt.verify(authorization[1], secretKey)
        console.log(payload)
        req.account = {
          userID: payload.sub,
          username: payload.name,
        }
      }

      next()
    } catch (error) {
      let err = error
      //console.log("ERROR")
      if (err.name === 'JsonWebTokenError' || err.name === 'TokenExpiredError') {
        err = createError(403)
        res.status(403).send({
          message: err.name
        })
      }
      next(err)
    }
  }
}