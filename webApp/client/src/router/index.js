import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Register from '@/components/Register'
import Login from '@/components/Login'
import Services from '@/components/Services/Index'
import ViewService from '@/components/ViewService'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/register',
      name: 'register',
      component: Register
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/services',
      name: 'services',
      component: Services
    },
    {
      path: '/services/:serviceId&:specialistId',
      name: 'service',
      component: ViewService
    },
    // {
    //   path: '/services/:serviceId&:specialistId',
    //   name: 'service',
    //   component: ViewService
    // }
  ]
})
