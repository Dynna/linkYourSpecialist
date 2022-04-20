import mongoose from 'mongoose'

const SpecialistsAvailabilityModel = new mongoose.Schema({
  userID: {
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
  }, 
  description: {
    type: String,
    required: false
  },
}, {
  versionKey: false,
  virtuals: true
})

/**
 * Creates and inserts new availability item.
 */
SpecialistsAvailabilityModel.statics.insert = async function (availabilityData) {
  const availability = new AvailabilityModel(availabilityData)
  return availability.save()
}

export const AvailabilityModel = mongoose.model('availability_item', SpecialistsAvailabilityModel)