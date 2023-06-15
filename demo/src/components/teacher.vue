<template>
    <div id="teacher">
      <div class="child">
        <span v-for="dt of ite">
          <a><img :src="require('../assets/教'+dt.id+'.jpg')" @click="getById(dt.id)"></a>
          <span>{{dt.name}}</span>
        </span>
      </div>

      <div id="detail-panel" v-show="loading">
        <ul class="details-left">
          <li>

<!--            加载失败时先注释编译后再取消注释-->
            <img class="details-left-img" :src="require('../assets/教'+detailData.id+'.jpg')" alt="教练的帅照">
<!--            <img class="details-left-img" :src="require(`${imgData}`)" alt="教练的帅照">-->
<!--            <img class="details-left-img" src="../assets/教3.jpg" alt="教练的帅照">-->
          </li>
        </ul>
        <ul class="details-right">
          <li>姓  名
            <br/>
            <span>{{detailData.name}}</span>
          </li>
          <li>专业 方向
            <br/>
            <span>{{detailData.xiangmu}}</span>
          </li>
          <li>成就 证书
            <br/>
            <span>{{detailData.beizhu}}</span>
          </li>
          <li>
            <a href="#"><img src="../assets/箭头上.png" class="png"></a>
<!--            <a @click.prevent="position('teacher')"><img src="../assets/箭头上.png" class="png"></a>-->
          </li>
        </ul>
      </div>

    </div>
</template>

<script>
  // import {queryRequestApplyPage} from '../api/test';
  import axios from "axios";
  export default {
    name: "teacher",
    data (){
      return {
        res :"error！",
        ite : "",
        errored : false, //判断后端是否出现问题
        loading : false,  //是否加载详情页
        detailData: {"id":2,"password":"2","name":"彭彩兰","xiangmu":"成人泳、体态调整","beizhu":"中国国家体育总局高级私人教练"},  // 详情页缓存
        // 教练员照片缓存地
        // imgData: ''
      }
    },
    beforeMount() {
      this.getAll();
    },
    methods: {
      async getAll(){
        await axios.get('/bigHomeWork/teacher/getAll').then((response) => {
          // console.log(response);
          this.ite = response.data.items
        }).catch(error => {
          console.log(error);
          this.errored = true
        }).finally(() => this.loading = false)
      },
      async getById(int){
        await axios.get('/bigHomeWork/teacher/getById?id='+int).then((response) => {
          // console.log(response);
          this.detailData = response.data.items[0];
          // this.imgData = response.data.items[0].img;
          this.position('detail-panel');
        }).catch(error => {
          console.log(error);
          this.errored = true
        }).finally(() => this.loading = true)
      },
      position(anchorName) {
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
      },
      createSRC(id) {
        console.log("传入的id为"+id);
        let src = "../assets/教"+id.toString()+".jpg";
        console.log("传出src="+src+"转换后");
        return src
      }
    }
  }
</script>

<style scoped>
  video{
    width: 100%;
  }
  .child img{
    width: 30%;
  }
  .child{
    height: 750px;
  }
  .child span{
    position: relative;
    margin: 130px 0;
    top: 60px;
  }
  .child span span{
    position: relative;
    writing-mode:vertical-lr;
    font-size: 18px;
  }
  ul{
    margin: 0 auto;
    list-style: auto;
    list-style: none;
  }
  ul>li{
    margin-top: 60px;
  }
  .details-left-img{
    width: 50%;
  }
  .details-left{
    height: 1230px;
    margin-top: 160px;
  }
  .details-right{
    position: relative;
    margin: -1280px 0 0 900px;
    line-height: 30px;
    height: 600px;
    width: 500px;
  }
  .png{
    width: 13%;
  }
</style>
