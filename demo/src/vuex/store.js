import Vue from "vue"
import Vuex from "vuex"

Vue.use(Vuex)

const store = new Vuex.Store({
  state:{
    shengfen:"游客",
    test:"测试",
  },
  getters: {}, //state的计算属性，会缓存P210
  mutations:{
    change: function (state,l){
      console.log("触发成功")
      state.shengfen = l
      console.log("状态修改为 " + state.shengfen)
    },
  },
  actions:{
    //异步操作状态方法213
  }
});

export default store
