package com.leo.redis;

import redis.clients.jedis.Jedis;

public class RedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("101.200.61.68");
        jedis.auth("kingredis");
    }
}
