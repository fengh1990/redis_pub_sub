package com.fengh.learnings.redislistener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RedisListenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisListenerApplication.class, args);
    }

}
