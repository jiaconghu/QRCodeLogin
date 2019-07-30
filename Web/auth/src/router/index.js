import Vue from 'vue'
import Router from 'vue-router'
import Auth from '../components/Auth'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      component: Auth
    },
    {
      path: '/auth',
      component: Auth
    }
  ]
})
