package com.moon.minisys.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 单机版redis客户端实现
 * （使用setter方法注入）
 * @author yujiangtao
 * @date 2018/7/13 16:28
 */
public class JedisClientSingle implements JedisClient {

    private JedisPool jedisPool;

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String str = jedis.get(key);
        jedis.close();
        return str;
    }

    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String str = jedis.set(key, value);
        jedis.close();
        return str;
    }

    public String hget(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        String str = jedis.hget(hkey, key);
        jedis.close();
        return str;
    }

    public long hset(String hkey, String key, String value) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hset(hkey, key, value);
        jedis.close();
        return result;
    }

    public long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.incr(key);
        jedis.close();
        return result;
    }

    public long expire(String key, int second) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.expire(key, second);
        jedis.close();
        return result;
    }

    public long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.ttl(key);
        jedis.close();
        return result;
    }

    public long del(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.del(key);
        jedis.close();
        return result;
    }

    public long hdel(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hdel(hkey, key);
        jedis.close();
        return result;
    }
}
