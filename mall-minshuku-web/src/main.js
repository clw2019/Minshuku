import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store/index'

// Element UI
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUI)

// 图片缩略图并支持预览插件
import vuePicturePreview from 'vue-picture-preview'
Vue.use(vuePicturePreview)

// 百度地图
import BaiduMap from 'vue-baidu-map'
Vue.use(BaiduMap, {
  ak: '8dbfrzBA4V0wclErug3WH7hou9BEdr3i'
})

// token 解析插件
// import jwt from 'jwt-decode';
// Vue.use(jwt);

Vue.config.productionTip = false

router.beforeEach((to, from, next) => {
  console.log("first........");

  if (to.meta.requireAuth) {
    console.log("two........");
    if (localStorage.getItem('token')) {
      console.log(localStorage.getItem('token'));

      console.log("three........");
      next()
    } else {
      console.log("five........");
      next({
        path: '/',
        // params :{
        //   dialogFormVisibleLogin: true
        // }

      })
    }
  } else {
    console.log("six........");
    next()
  }
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
