<template>
  <div>
    <h1>Register</h1>

    <input 
      type="text" 
      name="username" 
      v-model="username"
      placeholder="username" />
    <br>
    <input 
      type="email" 
      name="email" 
      v-model="email"
      placeholder="email" /> 
    <br>
    <input 
      type="text" 
      name="name" 
      v-model="name"
      placeholder="name" />
    <br>
    <input 
      type="text" 
      name="surname" 
      v-model="surname"
      placeholder="surname" />   
    <br>
    <input 
      type="password" 
      name="password" 
      v-model="password"
      placeholder="password" /> 
    <br>
    <button 
      @click="register">
      Register
    </button>

  </div>
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
      password: ''
    }
  },
  methods: {
    async register () {
      const response = await AuthenticationService.register({
        username: this.username,
        email: this.email,
        name: this.name,
        surname: this.surname,
        password: this.password
      })
      console.log(response.data)
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
