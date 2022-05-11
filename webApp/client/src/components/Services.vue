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
                            {{service.description | truncate(130, '...')}}
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
        
    },
    filters: {
        truncate: function (text, length, suffix) {
            if (text.length > length) {
                return text.substring(0, length) + suffix;
            } else {
                return text;
            }
        },
    }
}
</script>

<style scoped>
.service {
    padding: 20px;
    height: 210px;
    overflow: hidden;
    border-bottom: 4px solid #b5e7a0;
}

.service-name {
    font-size: 30px;
}

.service-category {
    font-size: 24px;
}

.service-description {
    font-size: 18px;
    margin-bottom: 5px;
}

</style>
