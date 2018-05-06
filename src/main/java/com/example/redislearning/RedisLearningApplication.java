package com.example.redislearning;

import com.example.redislearning.baseutil.DistributedLockUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;


@SpringBootApplication
public class RedisLearningApplication {


    public static void main(String[] args) throws  InterruptedException{

        //SpringApplication.run(RedisLearningApplication.class, args);

        /*Jedis jedis = new Jedis("47.98.115.186",6379);
        jedis.set("name","LP");
        String name = jedis.get("name");
        System.out.println(name);
        //查看有效期，-1表示持久化
        Long t = jedis.ttl("name");
        System.out.println(t);
        //已存在的key设置过期时间
        jedis.expire("name",50);
        try {
            while(true){
                String name2 = jedis.get("name");
                System.out.println(name2);
                System.out.println("有效期"+jedis.ttl("name")+"秒");
                Thread.sleep(2000);
            }
        } finally {
            jedis.close();
        }*/

        DistributedLockUtil.lock("LP");
    }
}
