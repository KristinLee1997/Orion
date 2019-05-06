package com.aries.orion.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.Set;

//https://357029540.iteye.com/blog/2399170
@RestController
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/gethash")
    public String gethash() {
        redisTemplate.opsForZSet().add("rank", 6L, 3L);
        redisTemplate.opsForZSet().add("rank", 3L, 4L);
        redisTemplate.opsForZSet().add("rank", 5L, 2L);
        printZset();
        System.out.println("end");
        redisTemplate.opsForZSet().incrementScore("rank", 20L, 5);
        printZset();
        return "test";
    }

    private void printZset() {
        Set<ZSetOperations.TypedTuple<Object>> rangeByScoreWithScores = redisTemplate.opsForZSet().reverseRangeWithScores("rank", 0, -1);
        Iterator<ZSetOperations.TypedTuple<Object>> iterator = rangeByScoreWithScores.iterator();
        while (iterator.hasNext()) {
            ZSetOperations.TypedTuple<Object> next = iterator.next();
            System.out.println("value:" + next.getValue() + " score:" + next.getScore());
        }
    }
}
