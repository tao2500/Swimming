<template>
  <div class="from_box">
    <form action="">
      <input type="text"  placeholder="请输入昵称" v-model="formMess.account">
      <input type="text" placeholder="请输入手机号" v-model="formMess.phone">
      请选择人群：
      <select v-model="formMess.input1" @change="change">
        <option v-for="it in crowdOption">{{it}}</option>
      </select>
      请选择性别：
      <select v-model="formMess.input2">
        <option v-for="it in genderOption">{{it}}</option>
      </select>
      请选择意向类别：
      <select v-model="formMess.input3">
        <option v-for="it in item">{{it}}</option>
      </select>
      班级类型：
      <select v-model="formMess.input4">
        <option v-for="it in classTypeOption">{{it}}</option>
      </select>
    </form>
    <br>
    <span class="but" @click="add()">确认报名</span>
  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    name: "from",
    data() {
      return {
        formMess:{
          "account": '',
          "phone": '',
          "input1": '',
          "input2": '',
          "input3": null,
          "input4": '',
        },
        mess: true,
        crowdOption: [],
        genderOption: ["男", "女"],
        classTypeOption: ["私教", "小班制"],
        item: ["先选人群"],
        childItem: [],
        adultItem: [],
        // 存储返回的信息
        detailData: '',
        // 游泳班信息
        classData: ''
      };
    },
    created() {
      this.init();
    },
    methods: {
      async add(){
        // if(this.input3 != null){
        if(this.formMess.account != '' && this.formMess.input1 != '' && this.formMess.phone != ''){
          await axios.get("/bigHomeWork/singUp/add?account="+[this.formMess.account,this.formMess.phone,this.formMess.input1,this.formMess.input2,this.formMess.input3,this.formMess.input4],
          ).then((response) => {
            console.log("返回的信息为"+response);
            this.detailData = response;
            this.$emit('show','报名成功！请等待教练员与您联系');
          }).catch(error => {
            console.log(error);
            this.errored = true
          })
        }else {
          console.log("yes"+this.formMess.account);
          this.$emit('show','请输入完整信息！');
        }

      },
      // 获取数据库中现有班级情况
      async init(){
        // if(this.input3 != null){
        await axios.get("/bigHomeWork/clas/getAll").then((response) => {
          this.classData = response.data.items;
          this.createData();
        }).catch(error => {
          console.log(error);
          this.errored = true
        })
      },
      // 将所有选项加入到option
      createData() {
        for(let l of this.classData){
          this.crowdOption.push(l.crowd);
          l.crowd === "儿童"? this.childItem.push(l.type) : this.adultItem.push(l.type)
        }
        this.crowdOption = [...new Set(this.crowdOption)],
        this.childItem = [...new Set(this.childItem)],
        this.adultItem= [...new Set(this.adultItem)]
        // this.classTypeOption = [...new Set(this.classTypeOption)]
      },
      change() {
        this.formMess.input1 === "儿童"? this.item = this.childItem : this.item = this.adultItem
      }
    }
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="less">
  .from_box{
    color: white;
    text-align: center;
    form{
      width:90%;
      margin: auto;
      border:.01rem solid gray;
      display: flex;
      flex-wrap: wrap;
      line-height: 30px;
      input{
        width:80%;
        height:1.50rem;
        margin-bottom: .2rem;
        border:.01rem solid black;
      }
      select{
        height: 1.50rem;
      }
    }
    .but{
      font-size: 1.5rem;
      color: green;
      cursor: pointer;
    }
  }
</style>
