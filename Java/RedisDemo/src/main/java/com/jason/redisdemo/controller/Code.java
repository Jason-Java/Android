package com.jason.redisdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * <p>描述:
 *
 *
 * <p>邮箱: fjz19971129@163.com
 *
 * @author 阿振
 * @version v1.0.0
 * @date：2022/10/27 13:37
 * @see
 */
@RestController
@RequestMapping
public class Code {


    @Autowired
    private RedisTemplate redisTemplate;


    @GetMapping("/hello")
    public String hello() {

        return "hello";
    }

    @GetMapping("/testRedis")
    public Boolean getCode() {
        Random random = new Random();
        String userId = String.valueOf(random.nextInt(50000));

        String prodKey = "kc:1010";
        String userKey = "ks:user";

        Object kc = redisTemplate.opsForValue().get(prodKey);
        if (prodKey == null) {
            System.out.println("秒杀还未开始!");
            return false;
        }


        if ((int) kc <= 0) {
            System.out.println("秒杀结束!");
            return false;
        }

        try {
            List<Object> results = (List<Object>) redisTemplate.execute(new SessionCallback<List<Object>>() {
                @Override
                public List<Object> execute(RedisOperations operations) throws DataAccessException {

                    operations.multi();
                    operations.opsForValue().decrement(prodKey);
                    operations.opsForSet().add(userKey, userId);
                    return operations.exec();
                }
            });
            System.out.println("秒杀成功!");
        } catch (Exception ignore) {
            System.out.println("网络延迟!请重试");
            return false;
        } finally {

        }

        return true;
    }


}
