<template>
<v-layout>
    <v-flex xs4>
        <panel title="Service details">
            <v-layout>
                <v-flex xs6>
                    <div class="service-name">
                        "{{servicepost.post.name}}"
                    </div>
                    <div class="service-category">
                        category: {{servicepost.post.category}}
                    </div>   
                </v-flex>

                <v-flex xs6>
                    <div class="service-description">
                        {{servicepost.post.description}}
                    </div>
                </v-flex>
            </v-layout>
        </panel>   
    </v-flex>
    <v-flex xs4>
        <panel title="Availability/ time slots to book the service" class="ml-2">

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
                    <div class="contacts">
                        Phone: {{servicepost.specialistInfo.phone}}
                    </div>
                    <div class="contacts">
                        Location: {{servicepost.specialistInfo.location}}
                    </div>   
                </v-flex>
            </v-layout>
        </panel>
    </v-flex>    
  </v-layout> 
</template>

<script>
import ServicePostsService from '@/services/ServicePostsService'
import Panel from '@/components/Panel'

export default {
    data () {
        return {
            servicepost: null
        }
    },
    async mounted () {
        const serviceId = this.$store.state.route.params.serviceId
        const specialistId = this.$store.state.route.params.specialistId
        this.servicepost = (await ServicePostsService.show(serviceId, specialistId)).data
        console.log('HETEEE',this.servicepost)
    },
    components:{
        Panel
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

</style>
