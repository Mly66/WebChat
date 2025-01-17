<template>
  <div class="room-container">
    <h3 class="header">当前用户：{{ this.username }}</h3>
    <h3 class="header">在线人数：{{ this.userNumber }}</h3>

    <div class="container">
      <div class="left">
        <h2 class="title">用户列表</h2>
        <ul class="user-list">
          <li v-for="user in userList" :key="user.id" :class="{ selected: user.selected }" @click="selectUser(user)">
            <div class="user-info">
              <span>{{ user.toUsername }}</span>
            </div>
          </li>
        </ul>
      </div>

      <div class="right">
        <div v-if="selectedUser">
          <h2 class="chat-header">
            正在与{{ selectedUser.toUsername }}聊天
          </h2>
        </div>
        <div v-if="selectedUser">
          <ul class="message-list">
            <li v-for="message in messageList[username + selectedUser.toUsername]" :key="message" class="message">
              {{ message }}
            </li>
          </ul>
        </div>

        <div v-if="selectedUser" class="message-input-container">
          <el-input v-model="selectedUserMessage.message" placeholder="请输入内容" @keyup.enter.native="sendMsg" class="message-input"/>
          <div class="button-container">
            <el-button type="primary" @click="sendMsg" class="send-button">发送消息</el-button>
            <el-button type="danger" @click="deleteAllMsgs" class="delete-button">删除所有消息</el-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getAllUsers, listPrivateMessages, deleteAllMsg } from "@/api";
import SockJS from "sockjs-client";
import Stomp from "stompjs";
import { Message } from "element-ui";

export default {
  name: "Room",
  data() {
    return {
      userList: [],
      selectedUser: null,
      messageList: {},
      username: "",
      userNumber: 1,
      selectedUserMessage: {
        user: null,
        message: "",
      },
      stompClient: null,
    };
  },
  methods: {
    listAllUsers() {
      getAllUsers(this.username).then((response) => {
        this.userNumber = ++response.data.userNumber;
        this.userList = response.data.friends.filter(
          (user) => user.toUsername !== this.username
        );
      });
    },
    selectUser(user) {
      if (!this.messageList[this.username + user.toUsername]) {
        this.$set(this.messageList, this.username + user.toUsername, []);
      }

      listPrivateMessages(this.username, user.toUsername).then((response) => {
        this.$set(this.messageList, this.username + user.toUsername, response.data);
      });

      this.selectedUser = user;
      this.selectedUserMessage.user = user;
      this.selectedUserMessage.message = "";
      this.userList.forEach((u) => u.selected = false);
      user.selected = true;
    },
    sendMsg() {
      if (this.stompClient !== null && this.selectedUserMessage.message !== "") {
        this.stompClient.send("/ClientToServer/privateChat", {}, JSON.stringify({
          fromUsername: this.username,
          message: this.selectedUserMessage.message,
          toUsername: this.selectedUserMessage.user.toUsername,
        }));

        this.messageList[this.username + this.selectedUserMessage.user.toUsername].push(
          this.username + " 发送：" + this.selectedUserMessage.message
        );

        this.selectedUserMessage.message = "";
      } else {
        Message.info("请输入消息");
      }
    },
    deleteAllMsgs() {
      if (!this.messageList[this.username + this.selectedUserMessage.user.toUsername].length) {
        Message.error("当前没有聊天记录");
        return;
      }
      deleteAllMsg(this.username, this.selectedUser.toUsername).then(() => {
        this.messageList[this.username + this.selectedUserMessage.user.toUsername] = [];
        Message.success("删除成功");
      });
    },
    connect() {
      const socket = new SockJS("http://127.0.0.1:8082/ws/mly");
      this.stompClient = Stomp.over(socket);
      this.stompClient.connect({}, (frame) => {
        this.stompClient.subscribe("/topic/ServerToClient.private." + this.username, (result) => {
          const res = JSON.parse(result.body);
          this.showContent(res.message, res.fromUsername, res.toUsername);
        });

        this.stompClient.subscribe("/topic/ServerToClient.deleteMsg", (result) => {
          const res = JSON.parse(result.body);
          this.messageList[res.toUsername + res.fromUsername] = [];
        });

        this.stompClient.subscribe("/topic/ServerToClient.showUserNumber", (result) => {
          this.userNumber = result.body;
        });
      });
    },
    disconnect() {
      if (this.stompClient !== null) {
        this.stompClient.disconnect();
      }
    },
    showContent(body, from, to) {
      if (!this.messageList[to + from]) {
        this.$set(this.messageList, to + from, []);
      }
      this.messageList[to + from].push(body);
    },
  },
  mounted() {
    this.username = sessionStorage.getItem("username");
    this.connect();
    this.listAllUsers();
  },
  beforeDestroy() {
    this.disconnect();
  },
};
</script>

<style scoped>
.room-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  background: linear-gradient(135deg, #f0f4f8, #d9e1e8);
  min-height: 100vh;
}

.header {
  font-size: 20px;
  color: #333;
  margin-bottom: 20px;
}

.container {
  display: flex;
  justify-content: space-between;
  width: 100%;
  max-width: 1200px;
}

.left {
  width: 30%;
  background-color: #ffffff;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  padding: 20px;
}

.title {
  text-align: center;
  font-size: 24px;
  color: #1a73e8;
  margin-bottom: 15px;
}

.user-list {
  list-style-type: none;
  padding: 0;
}

.user-list li {
  cursor: pointer;
  padding: 10px;
  border-radius: 8px;
  transition: background-color 0.3s ease;
}

.user-list li:hover {
  background-color: #f0f0f5;
}

.user-list li.selected {
  background-color: #e1f5fe;
  color: #1a73e8;
  font-weight: bold;
}

.right {
  width: 65%;
  background-color: #ffffff;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  padding: 20px;
}

.chat-header {
  text-align: center;
  font-size: 20px;
  color: #333;
  margin-bottom: 15px;
}

.message-list {
  list-style-type: none;
  padding: 0;
  margin-bottom: 20px;
}

.message {
  padding: 8px 12px;
  background-color: #f0f0f5;
  border-radius: 8px;
  margin-bottom: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.message-input-container {
  display: flex;
  flex-direction: column;
}

.message-input {
  margin-bottom: 15px;
  padding: 10px;
  border-radius: 12px;
  font-size: 16px;
  background-color: #f4f7fb;
  border: 1px solid #e1e7ef;
}

.button-container {
  display: flex;
  justify-content: space-between;
}

.send-button, .delete-button {
  width: 48%;
  padding: 10px;
  border-radius: 12px;
}

.send-button {
  background-color: #1a73e8;
  color: white;
}

.send-button:hover {
  background-color: #155ab0;
}

.delete-button {
  background-color: #e57373;
  color: white;
}

.delete-button:hover {
  background-color: #c84c4c;
}
</style>
