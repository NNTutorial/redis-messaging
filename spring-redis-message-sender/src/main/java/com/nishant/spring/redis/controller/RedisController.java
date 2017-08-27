package com.nishant.spring.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nishant.spring.redis.sender.MessageSender;
import com.nishant.spring.redis.vo.MessageVO;

@RestController
@RequestMapping("/redis")
public class RedisController {

	@Autowired
	private MessageSender messageSender;

	@Autowired
	private ObjectMapper objectMapper;


	@RequestMapping(value="/sendmsg",method=RequestMethod.POST)
	public String set(@RequestBody MessageVO redisVO) throws JsonProcessingException{
		return messageSender.sendmessage("key."+redisVO.getRoutekey()+".redis", objectMapper.writeValueAsString(redisVO).trim());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String invalidRequest(Exception e) {
		return "invalid request";
	}

}
