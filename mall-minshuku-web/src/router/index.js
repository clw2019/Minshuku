import Vue from 'vue'
import VueRouter from 'vue-router'

import Login from 'views/login/Login'
import Home from 'views/home/Home'
import Detail from 'views/detail/Detail'
import Order from 'views/order/Order'
import Center from 'views/mine/Center'
import MyOrder from "components/context/mineList/myOrder/MyOrder";
import EditComment from "components/context/mineList/myOrder/EditComment";
import MyCollect from "components/context/mineList/myCollect/MyCollect";
// import MyComment from "components/context/mineList/myComment/MyComment";
import MyHome from "components/context/mineList/myHome/MyHome";
import EditHome from "components/context/mineList/myHome/EditHome";
import EditReview from "components/context/mineList/myHome/EditReview";
import MyPersonal from "components/context/mineList/myPersonal/MyPersonal";
import OrderDetail from "components/context/mineList/myOrder/OrderDetail";
import Publish from "components/context/publish/Publish";
import MoreHome from "components/context/select/MoreHome";

// 解决两次访问路径相同报错
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/Home'
  },
  {
    path: '/home',
    name: 'Home',
    component: Home,
    meta: {
      keepAlive: true
    }
  },
  {
    path: '/detail/:id',
    name: 'Detail',
    component: Detail,
  },
  {
    path: '/orderdetail/:id',
    name: 'OrderDetail',
    component: OrderDetail,
  },
  {
    path: '/order',
    name: 'Order',
    component: Order,
    meta: {
      keepAlive: false
    }
  },
  {
    path: '/center',
    name: 'Center',
    component: Center,

    children: [
      {
        path: '/center/order',
        name: 'MyOrder',
        component: MyOrder, meta: { requireAuth: true },
      },
      {
        path: '/center/collect',
        name: 'MyCollect',
        component: MyCollect, meta: { requireAuth: true },
      },
      // {
      //   path: '/center/comment',
      //   name: 'MyComment',
      //   component: MyComment
      // },
      {
        path: '/center/myhome',
        name: 'MyHome',
        component: MyHome,
        meta: { requireAuth: true },
        // children: [{
        //   path: '/center/myhome/edithome/:id',
        //   name: 'EditHome',
        //   component: EditHome,
        // }]
      },

      {
        path: '/center/personal',
        name: 'MyPersonal',
        component: MyPersonal, meta: { requireAuth: true },
      },
      {
        path: '/editcomment/:id',
        name: 'EditComment',
        component: EditComment, meta: { requireAuth: true },
      },
      {
        path: '/edithome/:id',
        name: 'EditHome',
        component: EditHome, meta: { requireAuth: true },
      },
      {
        path: '/editreview/:id',
        name: 'EditReview',
        component: EditReview, meta: { requireAuth: true },
      }

    ]
  },
  {
    path: '/publish',
    name: 'Publish',
    component: Publish,
    meta: { requireAuth: true },
  },
  {
    path: '/morehome',
    name: 'MoreHome',
    component: MoreHome
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
]



const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
