<template>
    <panel title="Services">
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
                <div class="service-location">
                    {{service.location}}
                </div>

            <v-btn
                dark
                class="green darken-3"
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
                    {{service.description | truncate(160, '...')}}
                </div>
            </v-flex>
        </v-layout>
        </div>
    </panel>
</template>

<script>
import ServicePostsService from '@/services/ServicePostsService'
export default {
    data () {
        return {
            services: null
        }
    },
    watch: {
        '$route.query.search': {
            immediate: true,
            async handler (value) {
                this.services = (await ServicePostsService.index(value)).data
            }
        }
    },
    // async mounted () {
    //     this.services = (await ServicePostsService.index()).data
    //     console.log('services',this.services)
        
    // },
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
    height: 220px;
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

.service-location {
    font-size: 14px;
}

</style>
