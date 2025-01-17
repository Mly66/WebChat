<template>
  <div class="register-container">
    <h1 class="title">注册</h1>
    <router-link class="register-link" to="/login">已有账号？点我登录</router-link>
    <div class="input-container">
      <div class="input-row">
        <el-input
          class="input-field"
          type="text"
          placeholder="用户名(只包含0-9和小写a-z的字符串，且是5-10位)"
          v-model="username"
        ></el-input>
      </div>
      <div class="input-row">
        <el-input
          class="input-field"
          type="text"
          placeholder="昵称(1-10位)"
          v-model="nickname"
        ></el-input>
      </div>
      <div class="input-row">
        <el-input
          class="input-field"
          type="password"
          placeholder="密码(1-10位)"
          v-model="password"
        ></el-input>
      </div>
      <div class="input-row">
        <el-input
          class="input-field"
          type="password"
          placeholder="确认密码(1-10位)"
          @keyup.enter.native="register"
          v-model="passwordConfirm"
        ></el-input>
      </div>
    </div>

    <div class="button-container">
      <el-button class="register-button" type="primary" @click="register">注册</el-button>
    </div>
  </div>
</template>

<script>
import { userRegister } from "@/api";
import { Message } from "element-ui";

export default {
  name: "Register",
  data() {
    return {
      username: "",
      nickname: "",
      password: "",
      passwordConfirm: "",
    };
  },

  methods: {
    register() {
      if (this.password !== this.passwordConfirm) {
        Message.error("两次密码不一致");
        return;
      }
      userRegister(this.username, this.nickname, this.password)
        .then((response) => {
          Message.success("注册成功");
          this.$router.push("/login"); // 跳转到登录界面
        })
        .catch((error) => {
          // 处理错误
        });
    },
  },

  created() {},
};
</script>

<style scoped>
.register-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  background-color: #f0f0f5;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", "Roboto", "Helvetica Neue", "Arial", sans-serif;
  color: #333;
}

.title {
  font-size: 2rem;
  font-weight: bold;
  color: #333;
  margin-bottom: 20px;
}

.register-link {
  font-size: 1rem;
  color: #007aff;
  margin-bottom: 20px;
}

.input-container {
  width: 100%;
  max-width: 400px;
  padding: 20px;
  border-radius: 8px;
  background-color: #fff;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.input-row {
  margin-bottom: 15px;
}

.input-field {
  width: 100%;
  border-radius: 8px;
  border: 1px solid #ccc;
  padding: 10px;
  font-size: 1rem;
}

.button-container {
  margin-top: 20px;
}

.register-button {
  width: 100%;
  padding: 10px;
  font-size: 1rem;
  background-color: #007aff;
  border-color: #007aff;
  color: white;
  border-radius: 8px;
}

.register-button:hover {
  background-color: #005bb5;
  border-color: #005bb5;
}
</style>
