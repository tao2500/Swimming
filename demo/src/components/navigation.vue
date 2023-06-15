<template>
  <div className="title">
    <div className="title-left">
      <div className="title-left-image"></div>
      <router-link to="/bod">
        　　 <span className="">乐园首页</span>
      </router-link>
      <router-link to="/teacher">
        　　<span className="">特约教练</span>
      </router-link>
      <router-link to="/signUp">
        　　<span className="">游泳培训</span>
      </router-link>
      <router-link to="/activity">
        　　<span className="">近期活动</span>
      </router-link>
      <router-link to="/signIn">
        　　<span className="">登录账号</span>
      </router-link>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'Title',
  }
</script>
<style>
  span {
    font-size: 16px
  }

  a {
    width: 80px;
    height: 48px;
    color: white;
    /*margin: 0 10px;*/
    padding: 2px 10px;
    text-decoration: none;
    line-height: 48px;
    text-align: center;
    border-radius: 5px;
  }

  a:hover {
    background-color: white;
    color: black;
    transition: color 0.25s;
  }

</style>


