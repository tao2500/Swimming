<template>
  <div class="from_box">
    <form action="">
<!--      id由函数自动生成-->
      <input type="text"  placeholder="请输入昵称" v-model="formMess.account">
      <input type="password" placeholder="请输入密码" v-model="formMess.act_pwd">
      请选择人群：<select>
        <option>儿童</option>
        <option>成人</option>
      </select>
      请选择性别：<select>
      <option>男</option>
      <option>女</option>
    </select>
      请选择项目：<select>
      <option>蛙泳</option>
      <option>自由泳</option>
      <option>仰泳</option>
      <option>蝶泳</option>
      <option>花样游泳</option>
    </select>
      是否会员：<select>
      <option>是</option>
      <option>否</option>
    </select>
      会员时长：<select>
      <option>周卡</option>
      <option>月卡</option>
      <option>季卡</option>
      <option>年卡</option>
    </select>
      <input type="text" placeholder="请输入手机号" v-model="formMess.phone">
    </form>
    <span class="but" @click="onSubmit()">提交</span>
  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    name: "from",
    data() {
      return {
        formMess:{
          "account":"",
          "act_pwd":"",
          "phone":""
        },
        mess:true,
      };
    },
    methods: {
      onSubmit() {
        /* json格式提交： */
        // let formData = JSON.stringify(this.formMess);
        this.$emit('show', this.mess);
        /* formData格式提交： */
        let formData = new FormData();
        for(let key in this.formMess){
          formData.append(key,this.formMess[key]);
        }

        axios({
          method:"post",
          url:"xxxxxxx",
          headers: {
            "Content-Type": "multipart/form-data"
          },
          withCredentials:true,
          data:formData
        }).then((res)=>{
          console.log(res);
        });
      }
    }
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="less">
  .from_box{
    color: white;
    form{
      width:90%;
      margin: auto;
      border:.01rem solid gray;
      display: flex;
      flex-wrap: wrap;
      input{
        width:80%;
        height:.5rem;
        margin-bottom: .2rem;
        border:.01rem solid black;
        height:.5rem;
      }
    }
    .but{
      font-size: .14rem;
      margin-left:5%;
    }
  }
</style>
