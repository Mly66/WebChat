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
     * 
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
     * 
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
     * 
     * @param registry 配置消息代理
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 配置基于内存的消息代理
        registry.enableSimpleBroker(Constants.SIMPLE_BROKER);
        registry.setApplicationDestinationPrefixes(Constants.DESTINATION_PREFIXES);

       if (rabbitMQProperties != null && rabbitMQPropert
            registry.enableStompBrokerRelay(Constants.SIMPLE_BROKER)
                .setVirtualHost(rabbitMQProperties.getVirtualHost()
           tRelayHost(rabbitMQProperties.getHost())
           tRelayPort(5672)  // 修改为 AMQP 通信端口：5672
           tClientLogin(rabbit Properties.getUsernam
           tClientPasscode(rabbitMQProperties.getPassword())
           tSystemLogin(rabbitMQProperties.getUsername())
           tSystemPasscode(rabbitMQProperties.getPassword())
           tSystemHeartbeatSendInterval(5000)
           tSystemHeartbeatReceiveInterval(5000)
           
            log.war
           
           
}
