<template>
  <div class="login_container">
    <div class="login_box" v-show="box">
      <!-- 登录表单区域 -->
      <el-form size="mini">
        <!-- 用户名 -->
        <el-form-item>
          <el-input placeholder="用户名" v-model="name"></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item>
          <el-input placeholder="密码" v-model="pass" show-password></el-input>
        </el-form-item>
        <!-- 验证码 -->
        <el-form-item>
          <el-input  placeholder="验证码" v-model="inputCode" ></el-input>
        </el-form-item>
        <!-- 验证码图片-->
        <div class="logCode">
          <a @click="refreshCode">
            <!--验证码组件-->
            <s-identify :identifyCode="identifyCode" ></s-identify>
          </a>
          <p class="message">{{error}}</p>
          <p class="shengfen" v-if="shengfen">欢迎您：{{shengfen}}</p>
        </div>
        <el-form-item>
          <el-checkbox label="记住我" class="rememberMe" v-model="rememberMe"></el-checkbox>
        </el-form-item>
<!--         登录按钮-->
        <el-form-item>
          <el-button type="primary" @click="login">登录</el-button>
        </el-form-item>
        <br>
        <el-form-item>
          <el-button type="primary" @click="outLogin">退出登录</el-button>
        </el-form-item>
        <br/>
        <el-form-item>
          <el-button type="primary" @click="register">会员注册</el-button>
        </el-form-item>
        <br/>
        <el-form-item>
          <el-button type="primary" @click="modifyPass">修改密码</el-button>
        </el-form-item>
      </el-form>
    </div>


    <div class="sign-box" v-show="signBox">
      昵称：<input type="text" v-model="temp.name" />
      <br/><br/>
      密码：<input type="password" v-model="temp.password"/>
      <br/><br/>
      电话：<input type="password" v-model="temp.telephone"/>
      <br/><br/>
      人群：<select v-model="temp.crowd">
      <option v-for="item in statusOptions">{{item}}</option>
      </select>
      性别：<select v-model="temp.gender">
      <option v-for="item in genderOptions">{{item}}</option>
      </select>
      <br/><br/>
      会员：<select v-model="temp.huiyuan">
      <option v-for="item in huiyuanOptions">{{item}}</option>
      </select>&nbsp;&nbsp;&nbsp;
      <input type="button" value="注册" @click="sign" />
      <br/>
      <p class="showSignBoxMessage" v-show="showSignBoxMessage"> {{signBoxMessage}} </p>
    </div>

    <div class="sign-box" v-show="upDataBox">
      姓名：<input type="text" v-model="upData.name" />
      <br/><br/>
      原密码：<input type="password" v-model="upData.pass"/>
      <br/><br/>
      新密码：<input type="password" v-model="upData.newPass"/>
      <br/><br/>
      确认密码：<input type="password" v-model="upData.newPassTwo"/>
      <br/><br/>
      <input type="button" value="确认修改" @click="SubmitUpData" /> &nbsp
      <input type="button" value="取消修改" @click="modifyPass" />
      <br/>
      <p class="showSignBoxMessage" v-show="showUpDataMessage"> {{upDataBoxMessage}} </p>
    </div>
  </div>
</template>
<script>
  import SIdentify from './identify'
  import axios from "axios";
  import { mapState } from 'vuex';//210
  export default {
    name: "signIn",
    components: {SIdentify},
    data() {
      return {
        name: '',
        pass: '',
        inputCode: '',
        // 图片验证码
        identifyCode: '',
        error: '',
        // 记住我
        rememberMe: '',
        sf: '游客',  //暂存后端返回数据
        box: true,
        signBox: false,
        upDataBox: false,
        showSignBoxMessage: false,
        showUpDataMessage: false,
        signBoxMessage: '注册成功',
        upDataBoxMessage: '出错了',
        identifyCodes: '0123456789ABCDEFGHGKMNPQRSTUVWXYZ',
        baseError: false,  //读写数据库是否出错
        temp: {
          id: undefined,
          password: '',
          crowd: '',
          gender: '',
          name: '',
          telephone: '',
          xiangmu: '',
          huiyuan: '',
          overtime: new Date() // 开卡时间(格林威治时间)，再根据会员类型转换为截止时间
        },
        upData: {
          name: '',
          pass: '',
          newPass: '',
          newPassTwo: ''
        },
        statusOptions: ['儿童', '成人'],
        genderOptions: ['男', '女'],
        huiyuanOptions: ['大众周卡', '黄金月卡', '铂金季卡', '黑金年卡'],
        xiangmuOptions: ['自由泳', '蝶泳', '蛙泳', '仰泳', '花样游泳']
      }
    },
    beforeMount() {
      this.refreshCode();
      this.sf = this.$cookies.get('shengfen');
      this.name = this.$cookies.get('name');
      this.pass = this.$cookies.get('pass');
      this.$store.commit("change",this.sf);
    },
    computed: {
      ...mapState([
        'shengfen',
      ]),
    },
    methods: {
      // 切换验证码
      refreshCode() {
        this.identifyCode = ''
        this.makeCode(this.identifyCodes, 4)
      },
      // 生成随机验证码(参数分别为字符集与验证码个数)
      makeCode(o, l) {
        for (let i = 0; i < l; i++) {
          this.identifyCode += o[Math.floor(Math.random() * o.length)]
        }
      },
      login() {
        // this.inputCode === this.identifyCode
        if (this.inputCode === this.identifyCode) {
          if(this.pass === 666666){
            this.upDataBoxMessage = "首次登陆请修改密码!";
            this.upDataBox = this.upDataBox !== true;
            this.showUpDataMessage = true;
            setTimeout(() => {this.showUpDataMessage = false}, 2000);
          }
          this.Verify();
        } else {
          this.error = "输入验证码错误！";
          setTimeout(() => {
            this.error = "";
          }, 3000);
          this.refreshCode();
        }
      },
      async Verify() { //验证账号，保存身份
        await axios.get("/bigHomeWork/adminis/Verify?account=" + [this.name, this.pass]).then((response) => {
          this.sf = response.data;
          this.$cookies.set('shengfen',this.sf,"1h");
          this.$store.commit("change",{value:this.sf});
          this.change();
        }).catch(error => {
          console.log(error);
          this.baseError = true
        }).finally()
      },
      change() {
        if(this.rememberMe === true){
          console.log("记住我=" + this.rememberMe)
          this.$cookies.set('name',this.name,"244h");
          this.$cookies.set('pass',this.pass,"244h")
        }else {
          this.$cookies.set('name','',"244h");
          this.$cookies.set('pass','',"244h")
        }
        if (this.sf === "管理员") {
          setTimeout(() => {
            this.box = false
          }, 2000);
          setTimeout(() => {
            window.open('http://localhost:9527/#/dashboard?vehicleId="+ id','_blank');
            this.$router.push(`http://localhost:9527/#/dashboard?id=${4}`)
          }, 2000);
        }else if (this.sf === "查无此账号") {
          alert(this.sf);
          this.outLogin();
        } else {
            setTimeout(() => {
              this.box = false
            }, 2000);
        }
        this.$store.commit("change", this.sf);
      },
      // 会员注册
      register() {
        this.temp.id = '',
        this.temp.password = '',
        this.temp.crowd = '',
        this.temp.gender = '',
        this.temp.name = '',
        this.temp.telephone = '',
        this.temp.xiangmu = '',
        this.temp.huiyuan = '',
        this.temp.overtime = new Date(),
        this.signBoxMessage = '',
        this.showSignBoxMessage = false,
        this.signBox = this.signBox !== true
      },
      async sign() {
        if (this.temp.password !== '' && this.temp.name !== '' && this.temp.huiyuan !== '' && this.temp.telephone !== '') {
          this.changeOverTime(this.temp);
          this.temp.overtime = this.temp.overtime.toISOString();
          await axios.get("/bigHomeWork/student/add?account=" + [this.temp.password, this.temp.crowd, this.temp.gender, this.temp.name, this.temp.xiangmu, this.temp.huiyuan, this.temp.overtime, this.temp.telephone,'']
          ).then((response) => {
            this.signBoxMessage = response.data;
            this.showSignBoxMessage = true;
            setTimeout(() => this.signBox = false, 2000)
          }).catch(error => {
            console.log(error);
          })
        }else {
          this.signBoxMessage = "请输入完整信息"
          this.showSignBoxMessage = true;
          setTimeout(() => this.showSignBoxMessage = false, 2000)
        }
      },
      modifyPass() {
        this.upData.name = '',
        this.upData.pass = '',
        this.upData.newPass = '',
        this.upData.newPassTwo = '',
        this.upDataBoxMessage = "出错了",
        this.showUpDataMessage = false,
        this.upDataBox = this.upDataBox !== true
      },
      // 修改密码
      SubmitUpData() {
        if (this.upData.newPass !== this.upData.newPassTwo){
          this.upDataBoxMessage = "输入新密码不一致"
          this.showUpDataMessage = true;
          setTimeout(() => this.showUpDataMessage = false, 2000)
        }else {
          if (this.upData.newPass !== '' && this.upData.name !== '' && this.upData.pass !== '') {
            this.upPass()
          }else {
            this.upDataBoxMessage = "请输入完整信息"
            this.showUpDataMessage = true;
            setTimeout(() => this.showUpDataMessage = false, 2000)
          }
        }
      },
      async upPass() {
        await axios.get("/bigHomeWork/student/upPass?account=" + [this.upData.name, this.upData.pass, this.upData.newPass]
        ).then((response) => {
          this.upDataBoxMessage = response.data;
          this.showUpDataMessage = true;
          setTimeout(() => this.upDataBox = false, 2000)
        }).catch(error => {
          console.log(error);
        })
      },
      // 根据开卡时间算出结束时间
      changeOverTime(temp) {
        switch (temp.huiyuan) {
          case '大众周卡':
            this.temp.overtime = new Date(this.temp.overtime.setSeconds(this.temp.overtime.getSeconds() + 7 * 24 * 60 * 60))
            break
          case '黄金月卡':
            this.temp.overtime = new Date(this.temp.overtime.setMonth(this.temp.overtime.getMonth() + 1))
            break
          case '铂金季卡':
            this.temp.overtime = new Date(this.temp.overtime.setMonth(this.temp.overtime.getMonth() + 3))
            break
          case '黑金年卡':
            this.temp.overtime = new Date(this.temp.overtime.setMonth(this.temp.overtime.getMonth() + 12))
            break
          case '':
            this.temp.overtime = new Date(this.temp.overtime.setMonth(this.temp.overtime.getMonth() + 1))
          default :
        }
      },
      outLogin() {
        this.sf = '游客'
        this.$cookies.set('shengfen',this.sf,"244h")
        this.$store.commit("change",this.sf);
        this.$cookies.set('name','',"244h");
        this.$cookies.set('pass','',"244h")
      },
    }
  }
</script>
<style scoped>
  .login_container {
    /*background-image: linear-gradient(-180deg, #1a1454 0%, #0e81a5 100%);*/
    background-image: url("../assets/quanjin.jpg");
    background-repeat: no-repeat;
    background-size: cover;
    height: 800px;
    text-align: center;
  }
  .login_box {
    width: 300px;
    height: 500px;
    /* background-color: #fff; */
    background-color: #2e527bb3;
    border-radius: 5px;
    position: absolute;
    top: -50px;
    left: 1200px;
  }
  .el-form {
    padding: 32px;
    position: absolute;
    bottom: 0;
    width: 100%;
    box-sizing: border-box;
  }
  .el-button {
    width: 100%;
  }
  .code {
    width: 45%;
  }
  img {
    /* style="width: 100px; height: 30px; margin-left:5px;vertical-align: middle;" */
    display: inline;
    width: 45%;
    height: 48px;
    margin-left: 10px;
    vertical-align: middle;
    border-radius: 3px;
  }
  .rememberMe {
    display: inline-block;
    color: #fff;
    font-size: 20px;
    height: 30px;
  }
  .logCode{
    position: relative;
    margin: 0;
  }
  .logCode a{
    cursor: pointer;
  }
  .logCode a:hover{
    background: none;
  }
  .message{
    position: absolute;
    top: -6px;
    left: 60px;
  }
  .shengfen{
    position: relative;
    top: -30px;
  }
  .work{
    position: relative;
    height: 800px;
    top: 830px;
  }
  .sign-box {
    width: 300px;
    height: 200px;
    background-color: #2e527bb3;
    position: relative;
    top: 30px;
    left: 58%;
  }
  .showSignBoxMessage{
    color: black;
    font-size: 30px;
  }
</style>
