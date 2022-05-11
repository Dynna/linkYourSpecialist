<template>
    <panel title="Search">
      <v-text-field 
        label="Search by title, category" 
        v-model="search">

      </v-text-field>
    </panel>
</template>

<script>
export default {
  data () {
    return {
      search: ''
    }
  },
  watch: {
    search(value) {
      const route = {
        name: 'services'
      }
      if (this.search !== '') {
        route.query = {
          search: this.search
        }
      }
      this.$router.push(route)
    },
    '$route.query.search': {
      immediate: true,
      handler (value) {
        this.search = value
      }
    }
  }
}
</script>

<style scoped>

</style>
