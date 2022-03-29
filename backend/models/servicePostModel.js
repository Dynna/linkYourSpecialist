import mongoose from 'mongoose'

const ServicePostModel = new mongoose.Schema({
  userID: {
    type: String,
    required: true
  },
  category: {
    type: String,
    required: true
  },
  name: {
    type: String,
    required: true
  },
  description: {
    type: String,
    required: false
  },
  serveAtHome: {
    type: Boolean,
    required: false,
    default: false
  }
}, {
  versionKey: false,
  virtuals: true
})

/**
 * Creates and inserts new post.
 *
 * @param {object} postData - The post data.
 * @returns {Promise<Post>} The Promise to be fulfilled.
 */
ServicePostModel.statics.insert = async function (postData) {
  const post = new PostModel(postData)
  return post.save()
}

export const PostModel = mongoose.model('ServicePost', ServicePostModel)