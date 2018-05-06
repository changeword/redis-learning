package com.example.redislearning.baseutil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {

    private static JedisPool jedisPool;
    private static int maxTotal = 20;
    private static int maxIdle = 10;
    private static int minIdle = 5;
    private static boolean testOnBorrow = true;
    private static boolean testOnReturn = false;

    static {
        initPool();
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

    public static void close(Jedis jedis){
        jedis.close();
    }

    private static void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);
        config.setBlockWhenExhausted(true);
        jedisPool = new JedisPool(config,"47.98.115.186",6379,5000);
    }
}
