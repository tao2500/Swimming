export default {
  state: {
    // 管理的状态
    status:"游客",
    name:"",
    password:""
  },
  mutations:{
    // 定义待发送的事件，第一个参数会注入当前的state
    changeSF: function (state,l){
      console.log("触发成功")
      state.status = l
      console.log("状态修改为 " + state.status)
    },
    changeName: function (state,n){
      state.name = n
    },
    changePass: function (state,p){
      state.password = p
    }
  }
}
