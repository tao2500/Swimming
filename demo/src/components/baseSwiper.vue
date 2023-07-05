<template>
  <div class="swiperBox">
    <img class="imgLeft" @click="clickRight" src="../assets/箭头_左.png" alt="上一个">
    <img class="imgRight" @click="clickLeft" src="../assets/箭头_右.png" alt="下一个">
    <div id="swiper">
      <div class="imgBox">
        <div class="imgDiv" v-for="(item,index) of imgList" :key="index">
          <img :src="item" alt="场馆设施"/>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'BaseSwiper',
    props: {
      speed: Number,
      direction: String,
    },
    data() {
      return {
        imgList: [
          require('../assets/youyong6.jpg'),
          require('../assets/youyong6-1.jpg'),
          require('../assets/youyong7.jpg'),
          require('../assets/youyong8.jpg'),
          require('../assets/youyong9.jpg'),
        ],
        timer: null,
        theSpeed: this.speed,
        imgWidth: 260,
        theDirection: this.direction,
      }
    },
    methods: {
      clickLeft() {
        this.theDirection = 'left';
      },
      clickRight() {
        this.theDirection = 'right';
      },
    },
    mounted() {
      let imgBox = document.getElementsByClassName('imgBox')[0];
      //将imgBox下的图片进行拼接 循环展示图片
      imgBox.innerHTML += imgBox.innerHTML;
      let imgDiv = document.getElementsByClassName('imgDiv');
      imgBox.style.width = imgDiv.length * this.imgWidth + 'px';//设置div的宽度使图片可以放下
      let self = this;
      console.log(imgBox.offsetWidth,imgBox.style.width )
      function autoScroll() {
        if (imgBox.offsetLeft < -(imgBox.offsetWidth / 2)) {//提前更新left值，实现循环展示
          imgBox.style.left = 0;
        }
        if (imgBox.offsetLeft > 0) {//向右滚动 提前更新left值，实现循环展示
          imgBox.style.left = -(imgBox.offsetWidth / 2) + 'px';
        }
        if (self.theDirection === 'left') { //向左滚动,值为负
          self.theSpeed = -Math.abs(self.theSpeed)
        } else { //向右滚动
          self.theSpeed = Math.abs(self.theSpeed)
        }
        // 求出总的left值，等于left值加上移动的速度（px值）
        imgBox.style.left = imgBox.offsetLeft + self.theSpeed + 'px';
      }
      this.timer = setInterval(autoScroll, 30);//全局变量 ，保存返回的定时器
    },
    beforeDestroy() {
      clearInterval(this.timer);
      this.timer = null;
    }
  }
</script>
<style scoped lang='less' src="../css/baseSwiper.less"/>
