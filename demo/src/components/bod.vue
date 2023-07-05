<template xmlns="bigTX">
  <div>
    <img class="back" src="../assets/quanjin.jpg" alt="乐园全景">
    <div class="facilities">
      <span>
        <img src="../assets/a1.jpg" alt="亚特兰蒂斯水族馆"/>
        <p>亚特兰蒂斯水族馆</p>
      </span>
      <span>
        <img src="../assets/a3.jpg" alt="亚特兰蒂斯水上乐园"/>
        <p>亚特兰蒂斯水上乐园</p>
      </span>
      <span>
        <img src="../assets/youyong15.jpg" alt="亚特兰蒂斯竞技泳馆"/>
        <p>亚特兰蒂斯竞技泳馆</p>
      </span>
    </div>
    <div class="numMessage">
      <span class="message">
        <bigTX>134054.87</bigTX>  m²
        <p>建筑总面积</p>
      </span>
      <span class="message">
        <bigTX>160.37</bigTX>  m
        <p>建筑高度（最高点）</p>
      </span>
      <span class="message">
        <bigTX>5612</bigTX>  个
        <p>竞技馆座位数</p>
      </span>
    </div>
    <div class="mapMessage">
      <p>{{mess}}</p>
      <img src="../assets/map.jpeg" alt="乐园地图"/>
    </div>
    <div class="type">
      <span class="pool">
        <bigTx><img src="../assets/跳水.png" alt="比赛池"/> 比赛池 </bigTx><br/>
        50m(h)*25m(w)<br>
        水深：3m
      </span>
      <span class="pool">
        <bigTx><img src="../assets/游泳.png" alt="热身池"/> 热身池 </bigTx><br/>
        50m(h)*25m(w)<br>
        水深：0.9m-1.8m
      </span>
      <span class="pool">
        <bigTx><img src="../assets/儿童.png" alt="儿童池"/> 儿童池 </bigTx><br/>
        25m(h)*10m(w)<br>
        水深：1m-1.35m
      </span>
      <span class="pool">
        <bigTx><img src="../assets/奖杯.png" alt="跳水池"/> 跳水池 </bigTx><br/>
        25m(h)*25m(w)<br>
        水深：5.5m-6m
      </span>
      <span class="pool">
        <bigTx><img src="../assets/新闻.png" alt="新闻发布厅"/> 新闻发布厅 </bigTx><br/>
        总面积：218m²<br>
        高度：4m&nbsp;
        长度：16.6m*13.4m
      </span>
    </div>
    <div class="photoTag">
      <p class="text">PHOTO</p>
      <p>馆内照片</p>
    </div>
    <div class="equipmentPhotos">
      <img src="../assets/youyong6.jpg" alt="设施照片"/>
      <img src="../assets/youyong9.jpg" alt="设施照片"/>
      <img src="../assets/youyong7-1.jpg" alt="设施照片"/>
      <img src="../assets/youyong10-1.jpg" alt="设施照片"/>
      <img src="../assets/youyong9-1.jpg" alt="设施照片"/>
      <img src="../assets/youyong11.jpg" alt="设施照片"/>
    </div>
    <div class="businessHours">
      <span>
        <bigTx>BUSINESS<br/> HOURS</bigTx>
        <p>营业时间</p>
        <P>&nbsp;  &nbsp;除特别注明外、设施全年对外开放</P>
        <br/>
        <p>水上乐园/竞技泳馆</p>
        <p>&nbsp;  &nbsp;九月份-次年6月份  7:00-21:00</p>
        <p>&nbsp;  &nbsp;7-8月份 7:00-24：00</p>
      </span>
      <span class="callMe">
        <bigTx>CONTACT</bigTx>
        <p>联系电话</p>
        <br/>
        &nbsp;  &nbsp;13138047388 ~刘经理
      </span>
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

<style scoped lang="less" src="../css/bod.less"/>
