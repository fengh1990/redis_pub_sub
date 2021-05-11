package com.fengh.learnings.redislistener.config;

import com.fengh.learnings.redislistener.listener.MyMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import javax.annotation.Resource;
import java.util.Arrays;

@Configuration
public class RedisConfig {

    @Resource(name = MyMessageListener.COMPONENT_NAME)
    private MessageListener messageListener;

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory, MessageListenerAdapter messageListenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //__keyevent@0__:expired 过期的redis的key redis.conf配置文件配置 notify-keyspace-events "Ex"
        //tcp-keepalive 60
        container.addMessageListener(messageListenerAdapter, Arrays.asList(new PatternTopic("myChannel"), new PatternTopic("__keyevent@0__:expired")));
        return container;
    }

    /**
     * MessageListener适配器模式
     * @return
     */
    @Bean
    MessageListenerAdapter messageListenerAdapter() {
        return new MessageListenerAdapter(messageListener);
    }
}
