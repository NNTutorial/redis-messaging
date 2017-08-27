package com.nishant.spring.redis.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class MessageSender {

	@Autowired
	private RedisTemplate redisTemplate;
	
	public String sendmessage(String key,String message) {
		redisTemplate.convertAndSend(key, message);
		return "send for topic:"+key+"  Message is:"+message;
	}
}
 