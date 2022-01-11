const mongoose = require("mongoose");
const { Schema } = mongoose;
const validator = require('validator')

const MobileUserSchema = new Schema({
    username: {
        type: String,
        unique: 'The username is used by another user.',
        required: [true, 'Username is required.'],
        trim: true,
        minlength: [2, 'The username must contain at least {MINLENGTH} characters.'],
        maxlenght: [24, 'The username maximum length is {MAXLENGTH} characters.'],
        validate: [validator.isAlphanumeric, 'The username should contain only numbers and letters (a-z)']
    },
    email: {
        type: String,
        unique: 'The email is used by another user.',
        required: [true, 'Email is required.'],
        trim: true,
        maxlenght: [320, 'The email maximum length is {MAXLENGTH} characters.'],
        validate: [validator.isEmail, 'The email is not valid.']
    },
    name: {
        type: String,
        required: true,
        unique: false,
    },
    surname: {
        type: String,
        required: true,
        unique: false,
    },
    birthday: {
        type: Date,
        default: Date.now(),
        required: false,
    },
    categories: {
        type: [ String ],
        required: false,
        unique: false,
        default: [],
    },
    experience: {
        type: String,
        required: false,
        unique: false,
        default: '',
    },
    contactInfo: {
        phone: [ String ],
        address: {
            city: String,
            street: String,
            houseNumber: String,
        },
    },
    password: {
        type: String,
        required: [true, 'Password is required.'],
        minlength: [8, 'The password must be of minimum length {MINLENGTH} characters.'],
        maxlenght: [1000, 'The password maximum length {MAXLENGTH} characters.']
    },
});

MobileUserSchema.set('toJSON', {
    transform: (document, returnedObject) => {
        returnedObject.id = returnedObject._id.toString()
        delete returnedObject._id
        delete returnedObject.__v
        //do not reveal passwordHash
        delete returnedObject.password
    }
})

const MobileUser = mongoose.model("user", MobileUserSchema);

module.exports = MobileUser;