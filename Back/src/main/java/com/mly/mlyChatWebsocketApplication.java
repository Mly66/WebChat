package com.mly;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author mly
 * @create 2024-06-22 23:43
 */
@SpringBootApplication
@MapperScan("com.mly.mapper")
@PropertySource("classpath:secrets.txt")
public class mlyChatWebsocketApplication {
    public static void main(String[] args) {
        SpringApplication.run(mlyChatWebsocketApplication.class, args);
    }
}
