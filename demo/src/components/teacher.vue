<template>
    <div id="teacher">
      <div class="teachers">
        <span v-for="dt of ite">
          <a><img :src="require('../assets/教'+dt.id+'.jpg')" @click="getById(dt.id)" alt="教练照片"></a>
          <span>{{dt.name}}</span>
        </span>
      </div>

      <div id="detail-panel" v-show="loading">
        <ul class="details-left">
          <li>
            <img class="details-left-img" :src="require('../assets/教'+detailData.id+'.jpg')" alt="教练的帅照">
          </li>
        </ul>
        <ul class="details-right">
          <li>姓  名
            <br/>
            <span>{{detailData.name}}</span>
          </li>
          <li>专业 方向
            <br/>
            <span>{{detailData.item}}</span>
          </li>
          <li>成就 证书
            <br/>
            <span>{{detailData.order}}</span>
          </li>
          <li>
            <a href="#"><img src="../assets/箭头上.png" class="png" alt="返回顶部"></a>
          </li>
        </ul>
      </div>

    </div>
</template>

<script>
  import {getAllTeacher, getTeacher} from '../../api/teacher'
  export default {
    name: "teacher",
    data (){
      return {
        res :"error！",
        ite : "",
        errored : false, //判断后端是否出现问题
        loading : false,  //是否加载详情页
        detailData: {"id":2,"password":"2","name":"彭彩兰","item":"成人泳、体态调整","order":"中国国家体育总局高级私人教练"},  // 详情页缓存
      }
    },
    beforeMount() {
      this.getAll();
    },
    methods: {
      async getAll(){
        getAllTeacher().then((response) => {
          this.ite = response.items
        }).catch(error => {
          console.log(error);
          this.errored = true
        }).finally(() => this.loading = false)
      },
      async getById(int){
        getTeacher({id: int}).then((response) => {
          // console.log(response);
          this.detailData = response.items[0];
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
      }
    }
  }
</script>

<style lang="less" src="../css/teacher.less"/>
