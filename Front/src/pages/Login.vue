<template>
  <div class="login-container">
    <div class="content-box">
      <h1 class="title">登录</h1>
      <router-link class="register" to="/register">点我注册</router-link>
      <div class="input-container">
        <div class="input-row">
          <el-input
            type="text"
            placeholder="用户名"
            v-model="username"
            class="input-field"
          ></el-input>
        </div>
        <div class="input-row">
          <el-input
            type="password"
            placeholder="密码"
            @keyup.enter.native="loginEnterFun"
            v-model="password"
            class="input-field"
          ></el-input>
        </div>
      </div>
      <div class="button-container">
        <el-button type="primary" @click="login" class="login-btn">登录</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { userLogin } from "@/api";
export default {
  name: "Login",
  data() {
    return {
      username: "",
      password: "",
    };
  },

  methods: {
    loginEnterFun: function (e) {
      var keyCode = window.event ? e.keyCode : e.which;
      if (keyCode == 13) {
        this.login();
      }
    },

    login: function () {
      userLogin(this.username, this.password)
        .then((response) => {
          sessionStorage.setItem("username", this.username);
          this.$router.push({ path: "/room" });
        })
        .catch((error) => {
          // Handle error
        });
    },
  },

  created() {},
};
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: linear-gradient(145deg, #f0f4f8, #d9e1e8);
  padding: 20px;
}

.content-box {
  background-color: white;
  padding: 40px;
  border-radius: 12px;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
  text-align: center;
}

.title {
  font-size: 36px;
  color: #1a73e8;
  font-weight: 600;
  margin-bottom: 20px;
}

.register {
  color: #1a73e8;
  font-size: 14px;
  margin-top: 10px;
  text-decoration: none;
}

.register:hover {
  text-decoration: underline;
}

.input-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  margin-bottom: 20px;
}

.input-row {
  width: 100%;
  margin-bottom: 15px;
}

.input-field {
  border-radius: 15px;
  padding: 10px;
  font-size: 16px;
  background-color: #f4f7fb;
  border: 1px solid #e1e7ef;
  transition: all 0.3s ease;
}

.input-field:focus {
  border-color: #1a73e8;
  box-shadow: 0 0 5px rgba(26, 115, 232, 0.5);
}

.button-container {
  margin-top: 20px;
}

.login-btn {
  width: 100%;
  padding: 12px;
  font-size: 18px;
  border-radius: 15px;
  background-color: #1a73e8;
  color: white;
  border: none;
  transition: background-color 0.3s ease;
}

.login-btn:hover {
  background-color: #155ab0;
}
</style>
