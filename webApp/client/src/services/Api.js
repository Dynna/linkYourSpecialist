import axios from 'axios'

// exports
export default () => {
  return axios.create({
    baseURL: `http://localhost:4002/api/web/`
  })
}
