package com.fengh.learnings.redislistener.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 消息监听器
 * 功能：监听失效的redis的key
 */
@Component(value = MyMessageListener.COMPONENT_NAME)
public class MyMessageListener implements MessageListener {
    public static final String COMPONENT_NAME = "com.fengh.learnings.redislistener.listener.MyMessageListener";
    private static final Logger LOGGER = LoggerFactory.getLogger(MyMessageListener.class);

    private static final String STR_FORMATTER = "message:%s,channel:%s";

    @Override
    public void onMessage(Message message, byte[] bytes) {
        //接收的消息
        String body = new String(message.getBody());
        //监听的通道
        String channel = new String(message.getChannel());
        LOGGER.info(String.format(STR_FORMATTER, body, channel));
    }
}
