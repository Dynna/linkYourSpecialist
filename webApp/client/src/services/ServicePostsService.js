import Api from '@/services/Api'

export default {
  index (search) {
    return Api().get('services', {
      params: {
        search: search
      }
    })
  },
  //to add specialist ID somehow
  show (serviceId, specialistId) {
    return Api().get(`services/${serviceId}&${specialistId}`)
  }
}
