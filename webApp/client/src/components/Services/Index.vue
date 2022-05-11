<template>
    <v-layout column class="items-bg">
        <v-flex xs6 offset-xs3>
            <services-search-panel />
            <services-panel class="mt-2" />
        </v-flex>
    </v-layout>
</template>

<script>
import ServicesPanel from './ServicesPanel'
import ServicesSearchPanel from './ServicesSearchPanel'
import ServicePostsService from '@/services/ServicePostsService'
export default {
    components:{
        ServicesPanel,
        ServicesSearchPanel
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
