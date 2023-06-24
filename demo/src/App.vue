<template>
  <div id="app">
    <Title></Title>
    <Nav class="nav"></Nav>
    <div class="bod">
      <router-view ></router-view>
    </div>
    <Foot></Foot>
  </div>
</template>

<script>
  import Title from "./components/name";
  import Nav from "./components/navigation";
  import Bod from "./components/bod";
  import Foot from "./components/foot";
export default {
  name: 'App',
  components: {
    Title,
    Nav,
    Bod,
    Foot
  }
}

</script>

<style>
  #app{
    background-color: black;
    color: azure;
    margin: -20px 0 0 -8px;
  }
  .nav{
    position: relative;
    top: -90px;
    left: 1050px;
    margin: 0 1050px 0 0;
  }
  .bod{
    position: relative;
    top: -40px;
  }
  /*html * {*/
  /*  outline: 1px solid red*/
  /*}*/
</style>
