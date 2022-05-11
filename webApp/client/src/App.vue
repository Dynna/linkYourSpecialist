
<template>
  <div id="app">
    <v-app>
      <page-header />
      
      <main class="main-app">
        <v-container fluid>
          <router-view></router-view>
        </v-container>
      </main>
    </v-app>
  </div>
</template>

<script>
import PageHeader from '@/components/Header.vue'

export default {
  name: 'App',
  components: {
    PageHeader
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  /* margin-top: 60px; */
}

.danger-alert {
  color: red;
}

.main-app {
  /* background-image: url('home.png'); */
  background-color: rgba(195, 202, 195, 0.4);
  backdrop-filter: blur(5px);
}

</style>
