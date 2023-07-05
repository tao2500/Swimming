<template>
  <div class="foot">
    <div class="other">
      <span class="Introduction">
        <h2>场馆介绍 Venue</h2>
        <a v-for="x in ven" >{{x}}<br></a>
      </span>
      <span class="cooperate">
        <h2>商业合作 Cooperation</h2>
        <a v-for="x in coo" >{{x}}<br></a>
      </span>
      <span class="Activity">
        <h2>品牌活动 Activity</h2>
        <a v-for="x in act" >{{x}}<br></a>
      </span>
      <span class="about">
        <h2>关于我们 About</h2>
        <a v-for="x in abo" >{{x}}<br></a>
      </span>
    </div>
    <div class="copyright">
      <span class="contact">
      <img src="../assets/微信.png" alt="微信"/>
      <img src="../assets/微博.png" alt="微博"/>
      <img src="../assets/bilibili-square.png" alt="bilibili"/>
      <a href="#"><img src="../assets/箭头上.png" alt="返回顶部"></a>
        <!--      <a @click.prevent="totop('name')"><img src="../assets/箭头上.png"></a>-->
      </span>
      <br/>
      &nbsp;&nbsp;&copy;1998 版权所有:亚特兰蒂斯酒店管理有限公司
      <br/>
      京ICP备1998000001号-1&nbsp;Websupport:城池设计
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
      }
    }
  }
</script>

<style scoped lang="less" src="../css/foot.less"/>
