import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/views/login/Login'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/home',
    name: 'Home',
    component: () => import('@/views/home/Home.vue'),
    children: [{
      path: '/home/userlist',
      name: 'UserList',
      component: () => import('@/components/context/user/UserList')
    }, {
      path: '/home/homelist',
      name: 'HomeList',
      component: () => import('@/components/context/home/HomeList')
    }, {
      path: '/home/orderlist',
      name: 'OrderList',
      component: () => import('@/components/context/order/OrderList')
    }, {
      path: '/home/commentlist',
      name: 'CommentList',
      component: () => import('@/components/context/comment/CommentList')
    }]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
