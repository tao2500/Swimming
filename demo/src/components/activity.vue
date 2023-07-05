<template>
  <div>
    <div class="activity" v-for="dt of activity">
      <div class="imgBox">
        <img :src="require('../assets/'+dt.imgsrc+'.jpg')" alt="活动照片"/>
      </div>
      <div class="message">
        {{dt.name}}
        <p>报名时间：<br>
          {{ timeTrans(dt.startime) }} - {{ timeTrans(dt.overtime) }}</p>
        <span class="button" @click="onClick(dt.name)">{{button}}</span>
      </div>
    </div>
  </div>
</template>

<script>
  import {getAllActivity, joinActivity} from "../../api/activity";

  export default {
    name: "activity",
    data (){
      return{
        id: '',
        name: '',
        message: '',
        startime: '',
        overtime: '',
        imgsrc: require("../assets/青少年泳.jpg"),
        detailData: '',
        // 所有活动
        activity: {"id":2,"name":"第二十九届“亚特兰蒂斯杯”青少年自游泳锦标赛","message":"这是一段活动介绍","startime":"2022/8/1","overtime":"2022/8/25","imgsrc":"少年泳"},
        button: "点击报名",
        errored: false
      }
    },
    created() {
      this.getAll()
    },
    methods: {
      async getAll(){
        getAllActivity().then((response) => {
          this.detailData = response;
          this.activity = response.items
        }).catch(error => {
          this.errored = true
          console.log(error)
        }).finally(() => this.loading = false)
      },
      // 将格林威治时间转为北京时间
      timeTrans(timestamp) {
        let date = new Date(new Date(timestamp).getTime() + 8 * 3600 * 1000)
        date = date.toJSON()
        date = date.substring(0, 19).replace('T', ' ')
        return date
      },
      onClick(name) {
        if(this.$store.state.user.status === "游客" || this.$store.state.user.status === ''){
          alert("非会员/学员无法参赛！请先行注册")
        }else {
          this.add(name);
        }
      },
      async add(title){
        let score = prompt("请输入您的联系方式","");
        if (new RegExp(/(^1\d{10}$)|(^[0-9]\d{7}$)/).test(score)){
          joinActivity({account: [this.$store.state.user.status,score,title,new Date().toISOString()]},
          ).then((response) => {
            alert("报名成功！");
            this.detailData = response;
          }).catch(error => {
            console.log(error);
            this.errored = true
          })
        }else {
          alert("请输入正确的手机号！");
        }
      },
    }
  }
</script>

<style scoped src="../css/activity.css"/>
