import mongoose from 'mongoose'
import bcrypt from 'bcrypt'
import validator from 'validator'

const MobileUserSchema = new mongoose.Schema({
    username: {
        type: String,
        unique: [true,'The username is used by another user.'],
        required: [true, 'Username is required.'],
        trim: true,
        minlength: [2, 'The username must contain at least {MINLENGTH} characters.'],
        maxlenght: [24, 'The username maximum length is {MAXLENGTH} characters.'],
        validate: [validator.isAlphanumeric, 'The username should contain only numbers and letters (a-z)']
    },
    email: {
        type: String,
        unique: [true,'The email is used by another user.'],
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
}, {
    timestamps: true,
    versionKey: false,
    toJSON: {
      /**
       * Performs a transformation of the resulting object to remove sensitive information.
       *
       * @param {object} doc - The mongoose document which is being converted.
       * @param {object} ret - The plain object representation which has been converted.
       */
      transform: function (doc, ret) {
        delete ret._id
        delete ret.password
      },
      virtuals: true // ensure virtual fields are serialized
    }
  })

  MobileUserSchema.virtual('id').get(function () {
    return this._id.toHexString()
  })
  
  // Salts and hashes the password before save.
  MobileUserSchema.pre('save', async function () {
    this.password = await bcrypt.hash(this.password, 10)
  })
  
  /**
   * Authenticates a account.
   *
   * @param {string} email - The account's email.
   * @param {string} password - The account's password.
   * @returns {Promise<Account>} A promise that resolves into an object representing the account.
   */
  MobileUserSchema.statics.authenticate = async function (email, password) {
    const account = await this.findOne({ email })
  
    // If no account is found or password is wrong, throw an error.
    if (!account || !(await bcrypt.compare(password, account.password))) {
      const error = new Error()
      error.name = 'credentialsError'
      throw error
    }
  
    return account
  }
  
  /**
   * Creates and inserts a new account.
   *
   * @param {object} accountData - The account data.
   * @returns {Promise<Account>} The Promise to be fulfilled.
   */
  MobileUserSchema.statics.insert = async function (accountData) {
    const account = new MobileAccount(accountData)
    return account.save()
  }
  
  // Create a model using the schema.
  export const MobileAccount = mongoose.model('MobileUser', MobileUserSchema)