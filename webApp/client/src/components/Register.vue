<template>
  <v-layout column class="items-bg">
    <v-flex xs6 offset-xs3>
      <panel title="Register">
        <form 
          name="link-specialist-form"
          autocomplete="off">
          <v-text-field
            label="Username"
            v-model="username"
          ></v-text-field>
          <v-text-field
            label="Email"
            v-model="email"
          ></v-text-field>
          <v-text-field
            label="Name"
            v-model="name"
          ></v-text-field>
          <v-text-field
            label="Surname"
            v-model="surname"
          ></v-text-field>
          <v-text-field
            label="Password"
            type="password"
            v-model="password"
            autocomplete="new-password"
          ></v-text-field>
        </form>
        <br>
        <div class="danger-alert" v-html="error" />
        <br>
        <v-btn
          dark
          class="green darken-3"
          @click="register">
          Register
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
      username: '',
      email: '',
      name: '',
      surname: '',
      password: '',
      error: null
    }
  },
  methods: {
    async register () {
      try {
        const response = await AuthenticationService.register({
          username: this.username,
          email: this.email,
          name: this.name,
          surname: this.surname,
          password: this.password
        })
        this.$store.dispatch('setToken', response.data.access_token)
        this.$store.dispatch('setUser', response.data.user)
        this.$router.push('/')
      } catch (error) {
        this.error = error.response.data.error
      }
    }
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.error {
  color: red;
}
</style>
