<template>
  <nav-bar class="top">
    <div slot="left">
      <div class="logo">
        <img src="~assets/img/common/logo.jpg" @click="backHome" alt />
      </div>
    </div>
    <div slot="right">
      <div v-if="isShow">
        <a @click="dialogFormVisibleLogin = true">登录</a> /
        <a @click="dialogFormVisibleRegister = true">注册</a>
        <!-- <el-button type="text" @click="dialogFormVisibleLogin = true">登录</el-button>/
        <el-button type="text" @click="dialogFormVisibleLogin = true">注册</el-button>-->
        <el-dialog
          title="登录"
          :visible.sync="dialogFormVisibleLogin"
          :before-close="cleanLoginContent"
          width="40%"
          top="25vh"
          center
        >
          <el-form :model="loginForm" ref="loginForm" status-icon :rules="loginRules">
            <el-form-item prop="username">
              <el-input
                placeholder="账号"
                prefix-icon="el-icon-user"
                v-model="loginForm.username"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input
                placeholder="密码"
                prefix-icon="el-icon-unlock"
                v-model="loginForm.password"
                autocomplete="off"
                type="password"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-switch v-model="value1" active-text="记住密码"></el-switch>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="btnLogin('loginForm')" style="width:100%">登录</el-button>
              <p style="text-align:center">
                还没有注册？
                <el-button type="text" @click="changeRegisterDialog">注册>></el-button>
                <!-- <a href style="color: #f05b72">注册>></a> -->
              </p>
            </el-form-item>
          </el-form>
        </el-dialog>
        <el-dialog
          title="注册"
          :visible.sync="dialogFormVisibleRegister"
          :before-close="cleanRegisterContent"
          width="40%"
          top="25vh"
          center
        >
          <el-form :model="registerForm" ref="registerForm" status-icon :rules="registerRules">
            <el-form-item prop="username">
              <el-input
                placeholder="账号"
                prefix-icon="el-icon-user"
                v-model="registerForm.username"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input
                placeholder="请输入密码"
                prefix-icon="el-icon-unlock"
                v-model="registerForm.password"
                autocomplete="off"
                type="password"
              ></el-input>
            </el-form-item>
            <el-form-item prop="repeatPassword">
              <el-input
                placeholder="请确认密码"
                prefix-icon="el-icon-unlock"
                v-model="registerForm.repeatPassword"
                autocomplete="off"
                type="password"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="btnRegister('registerForm')" style="width:100%">注册</el-button>
              <p style="text-align:center">
                已有账号？
                <el-button type="text" @click="changeLoginDialog">登录>></el-button>
                <!-- <a href style="color: #f05b72">登录>></a> -->
              </p>
            </el-form-item>
          </el-form>
        </el-dialog>
      </div>
      <div v-else>
        <span class="welcome">欢迎您，</span>
        <el-avatar :size="30" class="avatar" :src="icon"></el-avatar>
        <el-dropdown>
          <span class="el-dropdown-link">
            <!-- vuex{{$store.state.username}}
            id{{$store.state.userId}}123-->
            {{getUserName}}
            <i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item
              v-for="(item, index) in this.person"
              :key="index"
              @click.native="changeClick(index)"
              style="text-align: center"
            >{{item}}</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <el-button type="primary" @click="btnClick" style="margin-left: 20px">免费发布房间</el-button>
        <!-- <el-button type="primary" @click="dialogVisiblePublish = true" style="margin-left: 20px">免费发布房间</el-button>
        <publish-dialog :dialogVisiblePublish="dialogVisiblePublish" />-->
      </div>
    </div>
  </nav-bar>
</template>

<script>
import jwt from "jwt-decode";
import NavBar from "components/common/navbar/NavBar";

import { register, login } from "network/user.js";
export default {
  name: "TopBar",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.registerForm.repeatPassword !== "") {
          this.$refs.registerForm.validateField("repeatPassword");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.registerForm.password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      dialogVisiblePublish: false,

      isShow: true,
      person: ["我的订单", "我的收藏", "我的房源", "个人资料", "退出"],
      value1: true,
      userName: "",
      icon: "",

      dialogFormVisibleLogin: false,
      dialogFormVisibleRegister: false,

      loginForm: {
        username: "",
        password: ""
      },
      registerForm: {
        username: "",
        password: "",
        repeatPassword: ""
      },
      // 表单校验规则
      loginRules: {
        username: [{ required: true, message: "请输入账号", trigger: "blur" }],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }]
      },
      registerRules: {
        username: [
          { required: true, message: "请输入账号", trigger: "blur" },
          { min: 11, max: 11, message: "长度在必须是11位" },
          {
            // pattern: /^(?!\d+$)[\da-zA-Z_]+$/,
            pattern: /^[0-9]+$/,
            message: "至少有一个字母，且不能有特殊符号"
          }
        ],
        password: [
          { validator: validatePass, trigger: "blur" },
          { min: 3, max: 12, message: "长度在3到12个字符" },
          // {
          //   pattern: /^(?!\d+$)[\da-zA-Z_]+$/,
          //   message: "至少有一个字母，且不能有特殊符号"
          // }
        ],
        repeatPassword: [
          { validator: validatePass2, trigger: "blur" },
          { min: 3, max: 12, message: "长度在3到12个字符" },
          // {
          //   pattern: /^(?!\d+$)[\da-zA-Z_]+$/,
          //   message: "至少有一个字母，且不能有特殊符号"
          // }
        ]
      }
    };
  },
  components: {
    NavBar
  },
  activated() {
    if (localStorage.getItem("isShow")) {
      this.isShow = false;
    }

    // // 使用vuex解决用户名刷新数据丢失
    // if (localStorage.getItem("username")) {
    //   this.$store.replaceState(
    //     Object.assign(
    //       {},
    //       this.$store.state,
    //       JSON.parse(localStorage.getItem("username"))
    //     )
    //   );
    // }
    // //在页面刷新时将vuex里的信息保存到localStorage里
    // window.addEventListener("beforeunload", () => {
    //   localStorage.setItem("username", JSON.stringify(this.$store.state));
    // });

    // console.log("home activated ==> ", localStorage.getItem("username"));
    // console.log("user 123---> ", localStorage.getItem("username"));
    // console.log("userId 123---> ", localStorage.getItem("userId"));
    // console.log("123---> ", localStorage.getItem("123"));
  },
  computed: {
    // 使用计算属性解决页面刷新数据丢失问题，计算属性会被缓存
    getUserName() {
      this.icon = localStorage.getItem("icon");
      return localStorage.getItem("username");
    },
    getUserId() {
      return localStorage.getItem("userId");
    }
    // getUserIcon() {
    //   return this.icon = localStorage.getItem("icon");
    // }
  },
  methods: {
    // 返回首页
    backHome() {
      this.$router.push("/Home");
    },

    btnLogin(formName) {
      // console.log("登录被点击了。。。", this.$store.state.token);
      this.$refs[formName].validate(valid => {
        if (valid) {
          console.log("登录被点击了。。。", this.loginForm);

          login(this.loginForm).then(res => {
            console.log(res);
            if (res.data.code == 100) {
              this.$message({
                message: res.data.message,
                type: "warning"
              });
            } else if (res.data.code == 200) {
              console.log("TOKEN id", res.data.data);

              //利用localstorage(永久保存)，localStorage(当前会话)存储到本地

              
              this.$store.commit("updateToken", res.data.data);
              localStorage.setItem("token", res.data.data);

              const decode = jwt(res.data.data);
              console.log("token 解析 => ", decode);
              console.log(decode.username);

              localStorage.setItem("username", decode.username);
              localStorage.setItem("userId", decode.userId);
              localStorage.setItem("icon", decode.icon);
              this.icon = localStorage.getItem("icon");
              console.log("icon is ", this.icon);

              console.log("user ---> ", localStorage.getItem("username"));

              this.userName = localStorage.getItem("username");

              localStorage.setItem("isShow", false);

              // console.log(
              //   "111111111111111111111",
              //   localStorage.getItem("isShow")
              // );

              if (localStorage.getItem("isShow")) {
                console.log("1325466", localStorage.getItem("isShow"));
                this.isShow = false;
              } else {
                console.log(".....");
              }

              // this.$store.dispatch()
              // this.$store.commit("updateToken", res.data.data);
              // this.$store.commit("updateUsername", this.loginForm.username);
              // this.$store.commit(
              //   "updateUsername",
              //   localStorage.getItem("username")
              // );
              // this.$store.commit("updateUserId", decode.userId);

              // this.isShow = false;

              // this.isShow = localStorage.setItem("isshow", "false");

              this.$message({
                message: res.data.message,
                type: "success"
              });
            }
          });
        } else {
          // console.log("error submit!!");
          return false;
        }
      });
    },
    btnRegister(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          console.log("注册被点击了。。。", this.registerForm);

          register(this.registerForm).then(res => {
            console.log(res);
            if (res.data.code == 100) {
              this.$message({
                message: res.data.message,
                type: "warning"
              });
            } else if (res.data.code == 200) {
              this.$message({
                message: res.data.message,
                type: "success"
              });
            }
          });
        } else {
          // console.log("error submit!!");
          return false;
        }
      });
    },
    // 关闭Dialog，清楚内容
    cleanLoginContent(done) {
      this.$nextTick(() => {
        this.$refs["loginForm"].resetFields();
        done();
      });
    },
    cleanRegisterContent(done) {
      this.$nextTick(() => {
        this.$refs["registerForm"].resetFields();
        done();
      });
    },
    // 切换到登录dialog方法
    changeLoginDialog() {
      this.dialogFormVisibleRegister = false;
      this.dialogFormVisibleLogin = true;
    },
    // 切换到登录dialog方法
    changeRegisterDialog() {
      this.dialogFormVisibleLogin = false;
      this.dialogFormVisibleRegister = true;
    },
    // 下拉选项的点击事件
    changeClick(index) {
      if (index == 0) {
        console.log("我的订单被点击了");
        this.$router.push("/center/order");
      }
      if (index == 1) {
        console.log("我的收藏被点击了");
        this.$router.push("/center/collect");
      }
      if (index == 2) {
        console.log("我的房源被点击了");
        this.$router.push("/center/myhome");
      }
      
      if (index == 3) {
        console.log("个人资料被点击了");
        this.$router.push("/center/personal");
      }
      if (index == 4) {
        console.log("退出被点击了");
        // 清空localStorage
        localStorage.clear();
        // localStorage.clear();
        // 跳转到首页
        this.$router.push("/Home");
        // 强制刷新,有一个刷新过程，不友好
        location.reload();
        // 让登陆按钮显示
        this.isShow = true;
        // 登陆Dialog隐藏
        this.dialogFormVisibleLogin = false;
      }
    },
    // 跳转到房屋发布页面
    btnClick() {
      if (localStorage.getItem("username")) {
        this.$router.push("/publish");
      } else {
        this.$message({
          message: "请先登录",
          type: "warning"
        });
      }
    }
  }
};
</script>

<style scoped>
.top {
  background-color: #ffffff;
  /* padding: 12px; */
}

.logo img {
  width: 70px;
  border-top: 15px;
}

.el-dropdown-link {
  cursor: pointer;
  color: var(--color-high-text);
}

.el-icon-arrow-down {
  font-size: 12px;
}

.welcome {
  font-family: "楷体";
  font-size: 18px;
}

.avatar {
  position: relative;
  top: 10px;
  left: -5px;
}
</style>