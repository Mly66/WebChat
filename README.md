# WebChat

## 项目介绍

`mly-chat-websocket` 是一个基于 Vue + Spring Boot + WebSocket 实现的聊天聊天室应用。该项目利用 WebSocket 实现实时双向通信，前端使用 Vue.js 构建，后端使用 Spring Boot 提供服务，并集成了 MySQL 和 RabbitMQ 实现消息存储和队列管理。

## 技术栈

### 后端

- **Spring Boot**：构建后端应用。
- **WebSocket**：实现实时通信。
- **MySQL**：用于存储聊天记录和用户信息。
- **RabbitMQ**：用于消息队列管理。
- **Swagger**：提供后端 API 文档。

### 前端

- **Vue.js**：前端框架。
- **Element UI**：UI 组件库。
- **SockJS**：WebSocket 客户端库。
- **STOMP.js**：WebSocket 协议的 JavaScript 实现。
- **Axios**：用于发送 HTTP 请求。

## 常见问题

1. **如何配置数据库？**
   - 请在 `application.yml` 文件中修改数据库连接配置。

2. **如何修改 WebSocket 端点？**
   - WebSocket 端点可以在后端 `WebSocketConfig` 类中进行修改。