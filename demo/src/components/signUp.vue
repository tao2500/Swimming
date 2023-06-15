<template>
  <div id="signUp">
    <div class="jianjiethree">
      <p class="jj">PHOTO</p>
      <p>馆内照片</p>
    </div>
    <div class="left">
      <swiper :speed="2" :direction="'left'"></swiper>
    </div>
    <div class="right">
      <login @show="showText"></login>
      <p>{{mess}}</p>
      <p class="out" v-show="state">{{out}}</p>
    </div>
    <div class="jianjieFour">
      <p class="jj">CLASS</p>
      <p>课程介绍</p>
    </div>
    <div class="classOn">
      <div class="el-icon" v-for="s of this.swimmingType">
        <div class="imgStyle"><img :src="require('../assets/'+s.name+'.jpg')"></div>
        <div class="el-icon-message">
          {{s.name}}<br><br>
          {{s.message}}<br><br>
          报名要求： {{s.requirement}}<br><br>
          教学目标： {{s.target}}<br><br>
          {{s.childPrice}}<br><br>
          {{s.adultPrice}}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import swiper from "./baseSwiper";
  import login from "./formBox";
  export default {
    name: "",
    components: {
      swiper,
      login
    },
    data(){
      return {
        mess:"亚特兰蒂斯以其丰富的教育资源、尽责的教练队伍，全心全意为体育运动爱好者提供最专业优质的服务。经过多年的组织经验磨练，本游泳中心师资力量、完善的教学设备及先进的教学模式在举办的各种活动中过程得到了充分的印证。承诺课程训练全程由职业教练教学。",
        out:"报名成功！请等待教练员与您联系",
        state:false,
        errState: false,
        showType: [],
        swimmingType: [{
          name: "蛙泳",
          message: '蛙泳一种模仿青蛙游泳动作的游泳姿势，也是一种最古老的泳姿。同时也是竞技游泳姿势之一。人体俯卧水面，两臂在胸前对称直臂侧下屈划水，两腿对称屈伸蹬夹水，似青蛙游水。蛙泳较省力，易持久，实用价值大，常用于渔猎、泅渡、救护、水上搬运等，同时，也是游泳初学者的学习项目。',
          requirement: '身体健康，身高1.2米以上',
          target: '熟悉水性，学习蛙泳，游15米及以上',
          childPrice: '私教课 ￥2598' + '小班课 ￥1988',
          adultPrice: '私教课 ￥3288' + '小班课 ￥2088'
        },{
          name: "蝶泳",
          message: '蝶泳技术是在蛙泳技术动作基础上演变而来的。当蛙泳技术发展到第二阶段时，在游泳比赛中，有些运动员采用两臂划水到大腿后提出水面，再从空中迁移的技术，从外形看，好像蝴蝶展翅飞舞，所以人们称它为“蝶泳”。',
          requirement: '已会蛙泳，游距15米以上,身体健康，身高1.2米以上',
          target: '巩固蛙泳技术，游25米及以上,学习蝶泳，游15米及以上',
          childPrice: '私教课 ￥4210' + '小班课 ￥2999',
          adultPrice: '私教课 ￥5288' + '小班课 ￥4088'
        },{
          name: "仰泳",
          message: '又称背泳，是一种人体仰卧在水中的游泳姿势。仰泳包括反蛙泳和反爬泳，因为脸面在水面上，呼吸很方便，但是游泳者看不到在往哪里游，容易错方向。仰泳是唯一运动员在水中开始的姿势，其它都是跳入水中。',
          requirement: '已会一种泳姿，游距15米以上,身体健康，身高1.2米以上',
          target: '巩固游泳技术，游25米及以上,学习仰泳，游15米及以上',
          childPrice: '私教课 ￥3688' + '小班课 ￥2688',
          adultPrice: '私教课 ￥4088' + '小班课 ￥2988'
        },{
          name: "自由泳",
          message:'爬泳又称为“自由泳”的代名词,游爬泳时，身体俯卧水面,两腿上下交替打水,两臂轮流向后划水,动作像爬行，故称爬泳。爬泳是速度最快的竞技泳姿,在可任选泳姿的自由泳比赛中被运动员广泛采用\n',
          requirement: '已会一种泳姿，游距15米以上,身体健康，身高1.2米以上',
          target: '巩固蛙泳技术，游25米及以上,学习自由泳，游15米及以上',
          childPrice: '私教课 ￥3688' + '小班课 ￥2688',
          adultPrice: '私教课 ￥4288' + '小班课 ￥3088'
        },{
          name: "花样游泳",
          message: '花样游泳是一项融入舞蹈和音乐的水上竞技项目，由游泳、技巧、舞蹈和音乐编排而成，有“水中芭蕾”之称',
          requirement: '已会多种泳姿，游距50米以上,身体健康，身高1.5米以上',
          target: '巩固游泳技术，游100米及以上,学习花样游泳',
          childPrice: '私教课 ￥8688' + '小班课 ￥5688',
          adultPrice: '私教课 ￥12288' + '小班课 ￥8888'
        },
        ],
      }
    },
    methods:{
      showText(data){
        // alert(data);
        this.state = true;
        this.out = data;
        setTimeout(()=>{this.state = false},3000);
      }
    }
  }
</script>

<style scoped>
  #signUp{
    margin: 40px 0 0 0;
  }
  .left{
    width: 70%;
    height: 500px;
    margin: 60px 0 90px 0;
  }
  .right{
    width: 26%;
    position: relative;
    top: -590px;
    left: 1100px;
  }
  .out{
    position: relative;
    top: 20px;
    text-align: center;
    color: red;
    font-size: 30px;
  }
  .jianjiethree{
    position: relative;
    left: 60px;
    top: -50px;
    width: 400px;
    height: 0px;
  }
  .jj{
    color: orange;
    font-size: 40px;
    margin: 10px 0 -10px 0;
  }
  .jianjieFour {
    position: relative;
    left: 60px;
    top: -350px;
    width: 400px;
    height: 0px;
  }
  .classOn{
    display: inline;
    width: 200px;
    height: 1500px;
    position: relative;
    top: -150px;
  }
  .el-icon{
    width: 30%;
    text-align: center;
    float: left;
    position: relative;
    left: 30px;
    margin: 40px 20px;
  }
  .imgStyle{
    height: 260px;
  }
  .el-icon-message{
    height: 350px;
    position: relative;
    top: 30px;
  }
</style>
