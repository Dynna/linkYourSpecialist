<template>
 <v-layout class="items-bg">
    <v-flex xs4>
         
    </v-flex>
    <v-flex xs4>
        <panel title="Booking service" class="ml-2">
            <div>
                <v-layout>
                    <v-flex xs12>
                        <div class="book-message">{{bookRequestInfo.message.message}}</div>
                        <br>
                        <div class="book-details"> You have booked the service for 
                            {{format_date(bookRequestInfo.book_request.date)}}
                            at {{bookRequestInfo.book_request.startTime}}.
                        </div>
                        <v-btn
                            dark
                            class="green darken-3"
                            :to="{
                                name: 'services',
                            }">
                            Go back
                        </v-btn> 
                    </v-flex>
                </v-layout>
            </div>
        </panel>
    </v-flex> 
    <v-flex xs4>
       
    </v-flex>    
  </v-layout> 
</template>

<script>
import ServicePostsService from '@/services/ServicePostsService'
import moment from 'moment'

export default {
    data () {
        return {
            bookRequestInfo: null,
        }
    },
    async mounted () {
        const specialistId = this.$store.state.route.params.specialistId
        const clientId = this.$store.state.route.params.clientId
        const clientEmail = this.$store.state.route.params.clientEmail
        const availabilityItemId = this.$store.state.route.params.availabilityItemId
        const date = this.$store.state.route.params.date
        const startTime = this.$store.state.route.params.startTime
        const endTime = this.$store.state.route.params.endTime
        const description = this.$store.state.route.params.description
        const specialistEmail = this.$store.state.route.params.specialistEmail
        const specialistName = this.$store.state.route.params.specialistName
        this.bookRequestInfo = (await ServicePostsService.insertBookRequest(specialistId, 
                                                                            clientId, 
                                                                            clientEmail,
                                                                            availabilityItemId,
                                                                            date,
                                                                            startTime,
                                                                            endTime,
                                                                            description,
                                                                            specialistEmail,
                                                                            specialistName
                                                                            )).data
      
        console.log('bookRequestInfo',this.bookRequestInfo)
    },
    methods: {
        format_date(value) {
         if (value) {
           return moment(String(value)).format('MMMM Do, YYYY')
          }
      },
    }
}
</script>

<style scoped>
.book-message {
    font-size: 24px;
}

.book-details {
    font-size: 20px;
    font-style: italic;
}
</style>
