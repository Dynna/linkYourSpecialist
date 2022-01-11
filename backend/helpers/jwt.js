const dotenv = require('dotenv');
const jwt = require('jsonwebtoken');

// get password vars from .env file
dotenv.config();

function authenticateToken(req, res, next) {
    const authHeader = req.headers['authorization']
    const token = authHeader && authHeader.split(' ')[1]

    if (token == null) return res.sendStatus(401)

    jwt.verify(token, process.env.TOKEN_SECRET_KEY, (err, user) => {
        console.log(err)

        if (err) return res.sendStatus(403)
        req.user = user
        next()
    })
}

function generateAccessToken(username) {
    return jwt.sign({data: username}, process.env.TOKEN_SECRET_KEY, { expiresIn: '6h' });
}

module.exports = {
    authenticateToken,
    generateAccessToken
}