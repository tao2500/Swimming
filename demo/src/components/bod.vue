<template>
  <div>
    <img class="back" src="../assets/quanjin.jpg">
    <div class="facilities">
      <img src="../assets/a1.jpg"/>
      <img src="../assets/a3.jpg"/>
      <img src="../assets/youyong15.jpg"/>
    </div>
    <div class="explain">
      <span>亚特兰蒂斯水族馆</span>
      <span>亚特兰蒂斯水上乐园</span>
      <span>亚特兰蒂斯竞技泳馆</span>
    </div>
    <br>
    <div class="jianjie">
      <div class="jj">
        <span>134054.87</span>  m²
        <p>建筑总面积</p>
      </div>
      <div class="jj">
        <span>160.37</span>  m
        <p>建筑高度（最高点）</p>
      </div>
      <div class="jj">
        <span>5612</span>  个
        <p>竞技馆座位数</p>
      </div>
    </div>
    <div class="jianjietwo">
      <p>{{mess}}</p>
    </div>
    <br>
    <img class="map" src="../assets/map.jpeg"/>
    <div class="x">
      <div class="xx">
        <div><img src="../assets/跳水.png"/> 比赛池 </div><br/>
        50m(h)*25m(w)<br>
        水深：3m
      </div>
      <div class="xx">
        <div><img src="../assets/游泳.png"/> 热身池 </div><br/>
        50m(h)*25m(w)<br>
        水深：0.9m-1.8m
      </div>
      <div class="xx">
        <div><img src="../assets/儿童.png"/> 儿童池 </div><br/>
        25m(h)*10m(w)<br>
        水深：1m-1.35m
      </div>
      <div class="xx">
        <div><img src="../assets/奖杯.png"/> 跳水池 </div><br/>
        25m(h)*25m(w)<br>
        水深：5.5m-6m
      </div>
      <div class="xx">
        <div><img src="../assets/新闻.png"/> 新闻发布厅 </div><br/>
        总面积：218m²<br>
        高度：4m&nbsp;
        长度：16.6m*13.4m
      </div>

    </div>
    <div class="jianjiethree">
      <p class="jj">PHOTO</p>
      <p>馆内照片</p>
    </div>
    <div class="swwing">
      <img src="../assets/youyong6.jpg"/>
      <img src="../assets/youyong9.jpg"/>
      <img src="../assets/youyong7-1.jpg"/>
      <img src="../assets/youyong10-1.jpg"/>
      <img src="../assets/youyong9-1.jpg"/>
      <img src="../assets/youyong11.jpg"/>
    </div>
    <div class="jinajiefour">
      <div class="done">
        <p class="jj">BUSINESS HOURS</p>
        <p>营业时间</p>
        <P>&nbsp;  &nbsp;除特别注明外、设施全年对外开放</P>
        <br/>
        <p>水上乐园/竞技泳馆</p>
        <p>&nbsp;  &nbsp;九月份-次年6月份  7:00-21:00</p>
        <p>&nbsp;  &nbsp;7-8月份 7:00-24：00</p>
      </div>
      <div class="dtwo">
        <p class="jj">CONTACT</p>
        <p>联系电话</p>
        <br/>
        &nbsp;  &nbsp;13138047388 ~刘经理
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "bod",
    data (){
      return {
        imgUrl:require("../assets/quanjin.jpg"),
        mess:"亚特兰蒂斯游泳竞技馆是国际级比赛场馆，按可举办国际单项赛事标准设计，包含六座50米\n" +
          "        *25米标准泳池，两座25米*25米标准跳水池，三座25米*10米短池。场馆 配有空调、地暖、\n" +
          "        暖气以及国际比赛标准的水循环系统及臭氧杀菌系统，室温适宜，水质优\n" +
          "        良，同时引入最先进的掌静脉生物识别技术，实现无卡化、无押金进出场，为运动爱好者提\n" +
          "        供现代化场馆的一流体验。"
      }
    }
  }
</script>

<style scoped>
  .back{
    width: 100%;
  }
  .facilities img{
    width: 30%;
    margin: 20px;
  }
  .explain span{
    margin: -20px 170px 0 180px;
  }
  .jianjie{
    color: orange;
  }
  .jianjie .jj{
    text-align: center;
    float: left;
    width: 400px;
    height: 300px;
    margin: 80px 40px 0 60px;
  }
  .jianjie .jj span{
    font-size: 70px;
  }
  .jianjietwo{
    line-height: 35px;
    width: 700px;
    margin: 60px 0 0 150px;
  }
  .map{
    width: 30%;
    margin: -230px 0 0 900px ;
  }
  .jianjiethree{
    margin: -100px 0 0 60px;
    width: 400px;
    height: 100px;
  }
  .jianjiethree .jj{
    color: orange;
    font-size: 40px;
    margin: 10px 0 -10px 0;
  }
  .swwing img{
    width: 30%;
    margin: 40px 10px 0 10px;
  }
  .jinajiefour div{
    display: inline-block;
    width: 400px;
    height: 550px;
    margin: 80px 0 0 250px;
  }
  .jinajiefour .jj{
    font-size: 70px;
    color: orange;
  }
  .dtwo{
    position: relative;
    top: -210px;
  }
  .xx{
    text-align: center;
    width: 290px;
    height: 350px;
    display: inline-block;
    line-height: 30px;
    margin: 40px 0 0 0;
  }
  .xx img{
    width: 50px;
    position: relative;
    top: 10px;
  }
  .xx div{
    font-size: 45px;
    color: orange;
  }
</style>
