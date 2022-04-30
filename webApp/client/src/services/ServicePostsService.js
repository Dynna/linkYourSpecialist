import Api from '@/services/Api'

export default {
  index () {
    return Api().get('services')
  },
  //to add specialist ID somehow
  show (serviceId, specialistId) {
    return Api().get(`services/${serviceId}&${specialistId}`)
  }
}
