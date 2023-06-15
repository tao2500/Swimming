import Vue from 'vue'
import Router from 'vue-router'
import axios from 'axios'  // 安装axios后引入
import bod from "../components/bod.vue"
import teacher from "../components/teacher.vue"
import signUp from "../components/signUp.vue";
import signIn from "../components/signIn.vue";
import activity from "../components/activity.vue";

Vue.prototype.$axios = axios  // 将axios挂载到原型上方便使用
Vue.use(Router)

export default new Router({
    //路由模式
    mode: 'hash',
    base: __dirname,
    routes: [
      { path: '/',component:bod},
      { path: '/bod',component:bod},
      { path: '/teacher', component: teacher},
      { path: '/signUp', component:signUp},
      { path: '/activity', component:activity},
      { path: '/signIn', component:signIn},
      { path: '*', redirect: '/' }
    ]
})
