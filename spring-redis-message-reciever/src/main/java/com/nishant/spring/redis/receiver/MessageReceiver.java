package com.nishant.spring.redis.receiver;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nishant.spring.redis.vo.MessageVO;

public class MessageReceiver {

	@Autowired
	private ObjectMapper objectMapper;

	public void receiveMessage1(String message) throws IOException {
		MessageVO msgvo=objectMapper.readValue(message.substring(message.indexOf("{"),message.lastIndexOf("}")+1), MessageVO.class);
		System.out.println("key1 is:key."+msgvo.getRoutekey()+".redis and message is:" + message.substring(message.indexOf("{"),message.lastIndexOf("}")+1));
	}
	public void receiveMessage2(String message) throws IOException {
		MessageVO msgvo=objectMapper.readValue(message.substring(message.indexOf("{"),message.lastIndexOf("}")+1), MessageVO.class);
		System.out.println("key2 is:key."+msgvo.getRoutekey()+".redis and message is:" + message.substring(message.indexOf("{"),message.lastIndexOf("}")+1));
	}
}
