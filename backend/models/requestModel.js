import mongoose from 'mongoose'

const RequestModel = new mongoose.Schema({
    specialistID: {
        type: String,
        required: true
    },
    clientID: {
        type: String,
        required: true
    },
    clientEmail: {
        type: String,
        required: true
    },
    availabilityItemID: {
        type: String,
        required: true
    },
    date: {
        type: Date,
        required: true,
        default: Date.now()
    },
    startTime: {
        type: String,
        required: true
    },
    endTime: {
        type: String,
        required: true
    }
}, {
    versionKey: false,
    virtuals: true
})

/**
 * Creates and inserts new request item.
 */
RequestModel.statics.insert = async function (requestData) {
    const request = new BookRequestModel(requestData)
    return request.save()
}

export const BookRequestModel = mongoose.model('book_time_request', RequestModel)