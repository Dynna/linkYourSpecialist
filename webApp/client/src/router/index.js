import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Register from '@/components/Register'
import Login from '@/components/Login'
import Services from '@/components/Services/Index'
import ViewService from '@/components/ViewService'
import BookRequest from '@/components/BookRequest'

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
    {
      path: '/services/:serviceId&:specialistId',
      name: 'service',
      component: ViewService
    },
    {
      path: '/services/:specialistId&:clientId&:clientEmail&:availabilityItemId&:description&:date&:startTime&:endTime&:specialistEmail&:specialistName',
      name: 'bookRequest',
      component: BookRequest
    },
    {
      path: '*',
      redirect: 'services'
    }
    // {
    //   path: '/services/:serviceId&:specialistId',
    //   name: 'service',
    //   component: ViewService
    // }
  ]
})
