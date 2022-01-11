import express from 'express'
import { router } from './routes/router.js'
import { connectDB } from './config/mongoose.js'

/**
 * The main function of the application.
 */
const main = async () => {
    await connectDB()

    const app = express()

    // Parse requests of the content type application/json.
    app.use(express.json())

    // Register routes.
    app.use('/', router)

    // Error handler.
    app.use(function (err, req, res, next) {
        err.status = err.status || 500
    })

    // Starts the HTTP server listening for connections.
    app.listen(4002, () => {
        console.log(`Server running at http://localhost:4002`)
        console.log('Press Ctrl-C to terminate...')
    })
}

main().catch(console.error)