package com.baizhi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.Jedis;

@SpringBootApplication
public class CmfzxypApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmfzxypApplication.class, args);
    }
    @Bean
    public Jedis getJedis(){
        return new Jedis("192.168.40.137",6379);
    }

}
