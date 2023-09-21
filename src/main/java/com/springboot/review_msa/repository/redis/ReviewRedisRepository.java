package com.springboot.review_msa.repository.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ReviewRedisRepository<T> {
    private final Class<T> targetType;
    private final RedisTemplate<String, T> redisTemplate;
    private final ObjectMapper objectMapper;
    private final Map<String, T> reviewMap = new HashMap<>();
    private final JSONParser parser = new JSONParser();


    public ReviewRedisRepository(Class<T> targetType, RedisTemplate<String, T> redisTemplate, ObjectMapper objectMapper) {
        this.targetType = targetType;
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    public <T> Map<String, T> getReviewKeyFromRedis(Collection<String> reviewIdList) {
        ValueOperations redis = redisTemplate.opsForValue();
        List<String> jsonList = redis.multiGet(reviewIdList);

        for(String json : jsonList) {
            mapJsonToReviewDTO(json);
        }

       return (Map<String, T>) reviewMap;
    }

    public void mapJsonToReviewDTO(String json) {
        try {
            try {
                JSONObject jsonObject = (JSONObject) parser.parse(json);
                reviewMap.put((String)jsonObject.get("reviewid"), objectMapper.readValue(json, targetType));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }catch (IOException e) {
            throw new RuntimeException("JSON 역직렬화 중 오류 발생", e);
        }
    }
}
