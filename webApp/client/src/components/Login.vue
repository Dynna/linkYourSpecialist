<template>
  <v-layout column class="items-bg">
    <v-flex xs6 offset-xs3>
      <panel title="Login">
        <v-text-field
          label="Email"
          v-model="email"
        ></v-text-field>
        <v-text-field
          label="Password"
          type="password"
          v-model="password"
        ></v-text-field>
        <div class="danger-alert" v-html="error" />
        <br>
        <v-btn
          dark
          class="green darken-3"
          @click="login">
          Login
        </v-btn>
      </panel>
    </v-flex>
  </v-layout>
</template>

// binding to controller
<script>
import AuthenticationService from '@/services/AuthenticationService'

export default {
  name: 'HelloWorld',
  data () {
    return {
      email: '',
      password: '',
      error: null
    }
  },
  methods: {
    async login () {
      try {
        const response = await AuthenticationService.login({
          email: this.email,
          password: this.password
        })
        this.$store.dispatch('setToken', response.data.access_token)
        this.$store.dispatch('setUser', response.data.user)
        this.$router.push('/') 
      } catch (error) {
        this.error = error.response.data.error
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
