package com.mly.config;

import com.mly.constants.Constants;
import com.mly.constants.RabbitMQProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * WebSocket 配置类，开启 STOMP 协议并进行消息代理配置
 */
@Configuration
@Slf4j
@EnableWebSocketMessageBroker
@EnableConfigurationProperties(RabbitMQProperties.class)
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private RabbitMQProperties rabbitMQProperties;

    /**
     * 构造器注入 RabbitMQ 配置
     * @param rabbitMQProperties 配置类，自动注入
     */
    @Autowired
    public WebSocketConfig(RabbitMQProperties rabbitMQProperties) {
        this.rabbitMQProperties = rabbitMQProperties;
        // 如果 RabbitMQ 配置项为空，则记录警告日志
        if (rabbitMQProperties != null && rabbitMQProperties.getHost() != null) {
            log.info("RabbitMQ host: {}", rabbitMQProperties.getHost());
        } else {
            log.warn("RabbitMQ properties not configured correctly, host is null");
        }
    }

    /**
     * 注册 STOMP 端点
     * @param registry 注册 STOMP 端点
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(Constants.ENDPOINT)
                .setAllowedOriginPatterns(Constants.ALLOWED_ORIGINS)
                .withSockJS();
    }

    /**
     * 配置消息代理
     * @param registry 配置消息代理
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 配置基于内存的消息代理
        registry.enableSimpleBroker(Constants.SIMPLE_BROKER);
        registry.setApplicationDestinationPrefixes(Constants.DESTINATION_PREFIXES);

        // 如果需要连接到 RabbitMQ 进行消息传递，取消注释下面的代码并配置 RabbitMQ
//        if (rabbitMQProperties != null && rabbitMQProperties.getHost() != null) {
//            registry.enableStompBrokerRelay(Constants.SIMPLE_BROKER)
//                    .setVirtualHost(rabbitMQProperties.getVirtualHost())
//                    .setRelayHost(rabbitMQProperties.getHost())
//                    .setRelayPort(5672)  // 修改为 AMQP 通信端口：5672
//                    .setClientLogin(rabbitMQProperties.getUsername())
//                    .setClientPasscode(rabbitMQProperties.getPassword())
//                    .setSystemLogin(rabbitMQProperties.getUsername())
//                    .setSystemPasscode(rabbitMQProperties.getPassword())
//                    .setSystemHeartbeatSendInterval(5000)
//                    .setSystemHeartbeatReceiveInterval(5000);
//        } else {
//            log.warn("RabbitMQ configuration is missing, using simple broker.");
//        }
    }
}
