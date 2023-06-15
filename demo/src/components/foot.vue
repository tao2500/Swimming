<template>
  <div class="foot">
    <div>
      <span class="fone">
        <h2>场馆介绍 Venue</h2>
        <a v-for="x in ven" >{{x}}<br></a>
      </span>
      <span class="ftwo">
        <h2>商业合作 Cooperation</h2>
        <a v-for="x in coo" >{{x}}<br></a>
      </span>
      <span class="fthree">
        <h2>品牌活动 Activity</h2>
        <a v-for="x in act" >{{x}}<br></a>
      </span>
      <span class="ffour">
        <h2>关于我们 About</h2>
        <a v-for="x in abo" >{{x}}<br></a>
      </span>
    </div>
    <div class="ffive">
      <br/>
      &nbsp;&nbsp;&copy;1998 版权所有:亚特兰蒂斯酒店管理有限公司
      <br/>
      京ICP备1998000001号-1&nbsp;Websupport:城池设计
    </div>
    <div class="navtwo">
      <img src="../assets/微信.png"/>
      <img src="../assets/微博.png"/>
      <img src="../assets/bilibili-square.png"/>
      <a href="#"><img src="../assets/箭头上.png"></a>
<!--      <a @click.prevent="totop('name')"><img src="../assets/箭头上.png"></a>-->
    </div>
  </div>
</template>

<script>
  export default {
    name: "foot",
    data (){
      return {
        ven:["亚特兰蒂斯酒店","亚特兰蒂斯水上乐园","亚特兰蒂斯竞技泳馆","亚特兰蒂斯海洋馆","亚特兰蒂斯综合馆","奥体公园","儿童公园","商户入驻"],
        coo:["包厢租赁","场地租赁","商户招商","其他合作"],
        act:["活动日历","精彩案例"],
        abo:["奥体概况","人才招聘","联系我们"],
        imgfour : '../assets/箭头上.png'
      }
    },
    methods:{
      totop(anchorName) {
        // 找到锚点
        let anchorElement = document.getElementById(anchorName);
        if(anchorElement) {
          let  total = anchorElement.offsetTop;//定位锚点
          //开始滚动
          // Chrome
          document.body.scrollTop = total
          // Firefox
          document.documentElement.scrollTop = total
          // Safari
          window.pageYOffset = total
        }

      }

    }
  }
</script>

<style scoped>
  .foot{
    background-color: #ff6f0b;
    color: black;
    height: 750px;
    overflow: hidden;
    clear: both;
  }
  a{
    font-style: oblique;
    color: black;
  }
  .foot div{
    width: 1000px;
    /*height: 500px;*/
  }
  .foot div span{
    display: inline-block;
    margin: 10px 80px;
    width: 300px;
    height: 400px;
    color: black;
  }
  .ftwo{
    position: relative;
    top: -190px;
  }
  .fthree{
    position: relative;
    top: -180px;
  }
  .ffour{
    position: relative;
    top: -130px;
  }
  .ffive{
    display: inline;
    position: relative;
    top: -800px;
    left: 1000px;
  }
  .navtwo img{
    width: 30px;
    height: 30px;
    border: 3px solid black;
    border-radius: 10px;
    display: inline;
    position: relative;
    top: -900px;
    left: 1100px;
  }
  .navtwo a{
    border-radius: 10px;
  }
</style>
