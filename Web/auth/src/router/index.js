import Vue from 'vue'
import Router from 'vue-router'
import Auth from '../components/Auth'
import Input from '../components/Input'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      component: Input
    },
    {
      path: '/auth',
      component: Auth
    },
    {
      path: '/input',
      component: Input
    }
  ]
})
