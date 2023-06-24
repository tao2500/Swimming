<template>
  <div id="activity">
    <div class="ac1" v-for="dt of pattry">
      <div class="bod">
        <img :src="require('../assets/'+dt.imgsrc+'.jpg')"/>
        <br/>
        <span class="message">
          {{dt.name}}
          <p>报名时间：<br>
            {{ timeTrans(dt.startime) }} - {{ timeTrans(dt.overtime) }}</p>
          <span class="button" @click="onClick(dt.name)">{{button}}</span>
        </span>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from "axios";

  export default {
    name: "activity",
    data (){
      return{
        id: '',
        name: '',
        messige: '',
        startime: '',
        overtime: '',
        imgsrc: require("../assets/青少年泳.jpg"),
        detailData: '',
        // 所有活动
        pattry: {"id":2,"name":"第二十九届“亚特兰蒂斯杯”青少年自游泳锦标赛","messige":"这是一段活动介绍","startime":"2022/8/1","overtime":"2022/8/25","imgsrc":"少年泳"},
        button: "点击报名",
        errored: false
      }
    },
    created() {
      this.getAll()
    },
    methods: {
      async getAll(){
        await axios.get('/bigHomeWork/activity/getAll').then((response) => {
          this.detailData = response;
          this.pattry = response.data.items
        }).catch(error => {
          this.errored = true
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
          await axios.get("/bigHomeWork/joinactivity/add?account="+[this.$store.state.user.status,score,title,new Date().toISOString()],
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

<style scoped>
  #activity img{
    width: 400px;
  }
  #activity{

  }
  .bod{
    display: inline-block;
    width: 50%;
    height: 300px;
    position: relative;
    text-align: center;
  }
  img{
    position: relative;
    margin: 0 0 50px 0;
  }
  .message{
    position: relative;
    left: 100px;
  }
  .ac1{
    display: inline-block;
    position: relative;
    margin: 80px 50px;
  }
  .button{
    color: red;
    font-size: 25px;
    cursor: pointer;
  }
</style>
