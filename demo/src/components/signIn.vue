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
          <p class="errMessage">{{error}}</p>
          <a @click="refreshCode">
            <!--验证码组件-->
            <s-identify class="linkImg" :identifyCode="identifyCode" ></s-identify>
          </a>
          <p class="welcome" v-if="sf">欢迎您：{{sf}}</p>
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
      会员：<select v-model="temp.member">
      <option v-for="item in memberOptions">{{item}}</option>
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
  import SIdentify from './identify';
  import {throttle} from "../js/throttle";
  import {checking, upPassWord, register} from "../../api/signIn"

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
        // 注册组件
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
          project: '',
          member: '',
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
        memberOptions: ['大众周卡', '黄金月卡', '铂金季卡', '黑金年卡'],
        projectOptions: ['自由泳', '蝶泳', '蛙泳', '仰泳', '花样游泳']
      }
    },
    beforeMount() {
      this.sf = this.$store.state.user.status;
      this.refreshCode();
      this.name = localStorage.getItem("name");
      this.pass = localStorage.getItem("pass");
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
      login: throttle(function () {
        // this.inputCode === this.identifyCode
        if (this.inputCode === this.identifyCode) {
          if (this.pass === 666666) {
            this.upDataBoxMessage = "首次登陆请修改密码!";
            this.upDataBox = !this.upDataBox;
            this.showUpDataMessage = true;
            setTimeout(() => {
              this.showUpDataMessage = false
            }, 0);
          }
          this.Verify();
        } else {
          this.error = "输入验证码错误！";
          setTimeout(() => {
            this.error = "";
          }, 3000);
          this.refreshCode();
        }
      }),
      async Verify() { //验证账号，保存身份
        checking({account: this.name + "," + this.pass}).then((response) => {
          this.sf = response;
          this.change();
        }).catch(error => {
          console.log(error);
          this.baseError = true
        }).finally();
      },
      change() {
        if (this.rememberMe === true) {
          console.log("记住我");
          localStorage.setItem("name", this.name);
          localStorage.setItem("pass", this.pass);
        }
        if (this.sf === "管理员") {
          setTimeout(() => {
            this.box = false
          }, 2000);
          setTimeout(() => {
            window.open('http://localhost:9527/#/dashboard?vehicleId="+ id', '_blank');
            this.$router.push(`http://localhost:9527/#/dashboard?id=${4}`)
          }, 2000);
        } else if (this.sf === "查无此账号") {
          alert(this.sf);
          this.outLogin();
        } else {
          setTimeout(() => {
            this.box = false
          }, 2000);
        }
        this.$store.commit("changeSF", this.sf);
      },
      // 会员注册
      register() {
        this.temp.id = '';
        this.temp.password = '';
        this.temp.crowd = '';
        this.temp.gender = '';
        this.temp.name = '';
        this.temp.telephone = '';
        this.temp.project = '';
        this.temp.member = '';
        this.temp.overtime = new Date();
        this.signBoxMessage = '';
        this.showSignBoxMessage = false;
        this.signBox = !this.signBox
      },
      sign: throttle(async function () {
        if (this.temp.password !== '' && this.temp.name !== '' && this.temp.member !== '' && this.temp.telephone !== '') {
          this.changeOverTime(this.temp);
          this.temp.overtime = this.temp.overtime.toISOString();
          register({account: [this.temp.password, this.temp.crowd, this.temp.gender, this.temp.name, this.temp.project, this.temp.member, this.temp.overtime, this.temp.telephone, '']}
          ).then((response) => {
            this.signBoxMessage = response;
            this.showSignBoxMessage = true;
            setTimeout(() => this.signBox = false, 2000)
          }).catch(error => {
            console.log(error);
          })
        } else {
          this.signBoxMessage = "请输入完整信息"
          this.showSignBoxMessage = true;
          setTimeout(() => this.showSignBoxMessage = false, 2000)
        }
      }),
      modifyPass() {
        this.upData.name = '';
        this.upData.pass = '';
        this.upData.newPass = '';
        this.upData.newPassTwo = '';
        this.upDataBoxMessage = "出错了";
        this.showUpDataMessage = false;
        this.upDataBox = !this.upDataBox
      },
      // 修改密码
      SubmitUpData: throttle(function () {
        if (this.upData.newPass !== this.upData.newPassTwo) {
          this.upDataBoxMessage = "输入新密码不一致"
          this.showUpDataMessage = true;
          setTimeout(() => this.showUpDataMessage = false, 2000)
        } else {
          if (this.upData.newPass && this.upData.name && this.upData.pass) {
            this.upPass()
          } else {
            this.upDataBoxMessage = "请输入完整信息"
            this.showUpDataMessage = true;
            setTimeout(() => this.showUpDataMessage = false, 2000)
          }
        }
      }),
      async upPass() {
        upPassWord({account: [this.upData.name, this.upData.pass, this.upData.newPass]}
        ).then((response) => {
          this.upDataBoxMessage = response;
          this.showUpDataMessage = true;
          setTimeout(() => this.upDataBox = false, 2000)
        }).catch(error => {
          console.log(error);
        })
      },
      // 根据开卡时间算出结束时间
      changeOverTime(temp) {
        switch (temp.member) {
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
        }
      },
      outLogin() {
        this.sf = '游客'
        this.$store.commit("changeSF", this.sf);
        localStorage.clear();
      },
    }
  }
</script>
<!--全局导入-->
<!--<link rel="stylesheet/less" type="text/css" href="../css/signIn.less"/>-->
<!--<script src="../css/less.js"></script>-->
<!--<style lang="less">-->
<!--  @import "../css/signIn";-->
<!--</style>-->
<!--局部导入-->
<style lang="less" src="../css/signIn.less"/>
