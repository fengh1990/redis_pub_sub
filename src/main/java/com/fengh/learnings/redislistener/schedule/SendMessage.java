package com.fengh.learnings.redislistener.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class SendMessage {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendMessage.class);
    private final StringRedisTemplate redisTemplate;
    private final Random RANDOM = new Random();

    public SendMessage(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Scheduled(fixedDelay = 5*1000)
    public void send() {
        int nextInt = RANDOM.nextInt(100);
        String message = String.format("message,%d", nextInt);
        String key = String.format("mykey%d", nextInt);
        redisTemplate.opsForValue().set(key, message, 10, TimeUnit.SECONDS);
        //向通道myChannel发送消息的方法
        redisTemplate.convertAndSend("myChannel", message);
    }
}
