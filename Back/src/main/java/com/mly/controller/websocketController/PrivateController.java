package com.mly.controller.websocketController;

import com.mly.domain.entity.PrivateMessage;
import com.mly.service.websocketService.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @Description: 私聊
 * @Date: 2023/6/24 17:47
 * @Author: mly
 */
@Controller
public class PrivateController {

    @Autowired
    private WebSocketService ws;

    @MessageMapping("/privateChat")
    public void singleChat(PrivateMessage message) {
        ws.sendChatMessage(message);
    }

}