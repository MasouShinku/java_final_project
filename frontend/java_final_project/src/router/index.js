import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Problemset from '../views/Problemset.vue'
import Detail from '../views/Detail.vue'
import Upload from '../views/Upload.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Login
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/problemset',
      name: 'problemset',
      component: Problemset
    },
    {
      path: '/detail',
      name: 'detail',
      component: Detail
    },
    {
      path: '/upload',
      name: 'upload',
      component: Upload
    }
  ]
})

export default router
