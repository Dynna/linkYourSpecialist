<template>
    <v-layout column>
        <v-flex xs6 offset-xs3>
            <panel title="services">
                <div 
                class="service"
                v-for="service in services" 
                :key="service._id">

                <v-layout>
                    <v-flex xs6>
                        <div class="service-name">
                            {{service.name}}
                        </div>
                        <div class="service-category">
                            {{service.category}}
                        </div>

                    <v-btn
                        dark
                        class="green"
                        :to="{
                            name: 'service',
                            params: {
                                serviceId: service._id,
                                specialistId: service.userID
                            }
                        }">
                        View
                    </v-btn>
                    </v-flex>

                    <v-flex xs6>
                        <div class="service-description">
                            {{service.description}}
                        </div>
                    </v-flex>
                </v-layout>
                </div>
            </panel>
        </v-flex>
    </v-layout>
 
</template>

<script>
import ServicePostsService from '@/services/ServicePostsService'
import Panel from '@/components/Panel'
export default {
    components:{
        Panel
    },
    data () {
        return {
            services: null
        }
    },
    async mounted () {
        this.services = (await ServicePostsService.index()).data
        console.log('services',this.services)
        
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

</style>
