<template>
<v-layout class="items-bg">
    <v-flex xs4>
        <panel title="Service details">
            <v-layout>
                <v-flex xs12>
                    <div class="service-name">
                        "{{servicepost.post.name}}"
                    </div>
                    <div class="service-category">
                        category: {{servicepost.post.category}}
                    </div> 
                    <div class="service-location">
                        location: {{servicepost.post.location}}
                    </div>   
                    <div class="service-description">
                        {{servicepost.post.description}}
                    </div>
                </v-flex>

                <!-- <v-flex xs6>
                    <div class="service-description">
                        {{servicepost.post.description}}
                    </div>
                </v-flex> -->
            </v-layout>
        </panel>   
    </v-flex>
    <v-flex xs4>
        <panel title="Availability/ time slots to book the service" class="ml-2">
            <div 
            class="availability-items" 
            v-for="availabilityItem in availabilityItems" 
            :key="availabilityItem._id">
                <v-layout>
                    <v-flex xs12>
                        <div class="">
                            Available: {{format_date(availabilityItem.date)}} 
                        </div>
                        <div class="">
                            Description: {{availabilityItem.description}}
                        </div>
                        <div class="">
                            Time: {{availabilityItem.startTime}} - {{availabilityItem.endTime}}
                        </div> 
                        <v-btn
                            dark
                            class="green darken-3"
                            :to="{
                                name: 'bookRequest',
                                params: {
                                    specialistId: servicepost.specialistInfo.id,
                                    clientId: userId,
                                    clientEmail: userEmail,
                                    availabilityItemId: availabilityItem._id,
                                    date: availabilityItem.date,
                                    startTime: availabilityItem.startTime,
                                    endTime: availabilityItem.endTime,
                                    description: availabilityItem.description,
                                    specialistEmail: servicepost.specialistInfo.email,
                                    specialistName: servicepost.specialistInfo.name
                                }
                            }">
                            Book
                        </v-btn> 
                    </v-flex>
                </v-layout>
            </div>
        </panel>
    </v-flex> 
    <v-flex xs4>
        <panel title="Specialist contacts" class="ml-2">
            <v-layout>
                <v-flex xs12>
                    <div class="contacts">
                        Name: {{servicepost.specialistInfo.name}} {{servicepost.specialistInfo.surname}}
                    </div>
                    <div class="contacts">
                        Email: {{servicepost.specialistInfo.email}}
                    </div>
                    <div class="contacts" v-if="servicepost.specialistInfo.phone">
                        Phone: {{servicepost.specialistInfo.phone}}
                    </div>
                    <div class="contacts" v-if="servicepost.specialistInfo.location">
                        Location: {{servicepost.specialistInfo.location}}
                    </div>   
                    <div class="contacts" v-if="servicepost.specialistInfo.experience">
                        Experience: {{servicepost.specialistInfo.experience}}
                    </div> 
                </v-flex>
            </v-layout>
        </panel>
    </v-flex>    
  </v-layout> 
</template>

<script>
import ServicePostsService from '@/services/ServicePostsService'
import moment from 'moment'

export default {
    data () {
        return {
            servicepost: null,
            availabilityItems: null,
            userId: this.$store.state.user.id,
            userEmail: this.$store.state.user.email
        }
    },
    async mounted () {
        const serviceId = this.$store.state.route.params.serviceId
        const specialistId = this.$store.state.route.params.specialistId
        this.servicepost = (await ServicePostsService.show(serviceId, specialistId)).data
        this.availabilityItems = this.servicepost.availability
        console.log('ALL SERVICES',this.servicepost)
        console.log('ONLY AVAILABILITY',this.availabilityItems)
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
.service {
    padding: 20px;
    height: 330px;
    overflow: hidden;
}

.service-name {
    font-size: 30px;
}

.service-category {
    font-size: 24px;
}

.service-description {
    font-size: 18px;
}

.contacts {
    font-size: 21px;
}

.availability-items {
    padding-bottom: 6px;
    padding-top: 6px;
    font-size: 19px;
    border-bottom: 3px solid #b5e7a0;
}

.service-location {
    font-size: 12px;
}

</style>
