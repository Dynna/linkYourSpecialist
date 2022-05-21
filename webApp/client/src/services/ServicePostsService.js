import Api from '@/services/Api'

export default {
  index (search) {
    return Api().get('services', {
      params: {
        search: search
      }
    })
  },
  
  show (serviceId, specialistId) {
    return Api().get(`services/${serviceId}&${specialistId}`)
  },

  insertBookRequest (specialistId, clientId, clientEmail, availabilityItemId, date, startTime, endTime, specialistEmail, specialistName) {
    return Api().post(`services/${specialistId}&${clientId}&${clientEmail}&${availabilityItemId}&${date}&${startTime}&${endTime}&${specialistEmail}&${specialistName}`)
  }
}
