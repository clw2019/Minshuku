<template>
  <div class="login">
    <nav-bar class="top-nav">
      <div slot="left">
        <img src="@/assets/img/logo.png" alt />
        <span>Minshuku</span>
      </div>
    </nav-bar>
    <div class="container">
      <el-dialog
        title="管理员登录界面"
        :visible.sync="dialogFormVisibleLogin"
        width="40%"
        top="25vh"
        center
        :modal="false"
        :show-close="false"
        :close-on-click-modal="false"
      >
        <el-form :model="loginForm" ref="loginForm" status-icon>
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
            <el-button type="primary" @click="btnLogin('loginForm')" style="width:100%">登录</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import jwt from "jwt-decode"

import NavBar from "@/components/common/navbar/NavBar";

import { login } from "@/network/login";
export default {
  name: "Login",
  data() {
    return {
      dialogFormVisibleLogin: true,
      loginForm: {
        username: "",
        password: ""
      }
    };
  },
  methods: {
    btnLogin() {
      console.log("login is clicked");
      login(this.loginForm).then(res => {
        if (res.data.code == 200) {
          console.log("token is -- ", res.data.data);
          const token = res.data.data;
          localStorage.setItem("username", jwt(token).username)
          
          this.$router.push("/home")
          this.$message({
            message: res.data.message,
            type: "success"
          });
        } else {
          this.$message({
            message: res.data.message,
            type: "warning"
          });
        }
      });
    }
  },
  components: {
    NavBar
  }
};
</script>

<style scoped>
.container {
  background-image: url("~@/assets/img/loginbg.jpg");
  height: 670px;
}

.top-nav {
  height: 60px;
  background: black;
}

.left img {
  position: relative;
  top: 9px;
  left: -10px;
}

.left span {
  color: white;
}
</style>