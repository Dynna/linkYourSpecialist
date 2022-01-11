const express = require('express');
const app = express();
const mongoose = require('mongoose');
const auth = require('./helpers/jwt.js')
const unless = require('express-unless')
const mobileUsers = require('./controllers/mobileAccountController.js')
const errors = require('./helpers/errorHandler.js')
const connectDB = require('./config/mongoose.js')

// middleware for authenticating token submitted with requests
auth.authenticateToken.unless = unless
app.use(auth.authenticateToken.unless({
    path: [
        { url: '/mobileUsers/login', methods: ['POST'] },
        { url: '/mobileUsers/register', methods: ['POST'] },
        { url: '/mobileUsers/', methods: ['GET'] }
    ]
}))
connectDB();
app.use(express.json()) // middleware for parsing application/json
app.use('/mobileUsers', mobileUsers) // middleware for listening to routes
app.use(errors.errorHandler); // middleware for error responses


/*// MongoDB connection, success and error event responses
const uri = "mongodb://localhost:27017/link_your_specialist";
mongoose.connect(uri, { useCreateIndex: true, useNewUrlParser: true, useUnifiedTopology: true });
const db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', () => console.log(`Connected to mongo at ${uri}`));*/

app.listen(4002, () => {
    console.log(`Server running at http://localhost:4002`)
    console.log('Press Ctrl-C to terminate...')
})