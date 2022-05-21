import axios from 'axios'

// exports
export default () => {
  return axios.create({
    // baseURL: `http://localhost:4002/api/web/`
    baseURL: `https://link-your-specialist-backend2.herokuapp.com/api/web/`
  })
}
