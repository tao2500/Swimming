<template>
  <div id="name">
    <h1>亚特兰蒂斯水世界</h1>
    <p>入住亚特兰蒂斯度假酒店，尽情探索忘却繁琐的快乐天地。每家度假酒店皆提供布置精美的客房，更有水世界冒险乐园供客人畅玩，享誉全球。</p>
  </div>
</template>

<script>
  export default {
    name: "name"
  }
</script>

<style scoped>
  #name{
    position: relative;
    top: 12px;
    left: 15px;
    /*防止由于left属性产生的水平滚动条*/
    margin-right: 10px;
  }
  h1{
    font-size: 30px;
    color: azure;
  }
  p{
    font-size: 16px;
  }
</style>
