package com.nishant.spring.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nishant.spring.redis.property.RedisProperty;
import com.nishant.spring.redis.receiver.MessageReceiver;

@Configuration
@Profile(value= {"redis"})
public class RedisConfig {

	@Autowired
	private RedisProperty redisProperty; 

	@Bean
	public JedisConnectionFactory jedisConnFactory() {
		JedisConnectionFactory jd=new JedisConnectionFactory();
		jd.setUsePool(true);
		jd.setHostName(redisProperty.getRedis().getHost());
		jd.setPort(Integer.parseInt(redisProperty.getRedis().getPort()));
		return jd;
	}

	@Bean
	public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,MessageReceiver receiver) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(jedisConnFactory());
		container.addMessageListener(listenerAdapter1(receiver), new PatternTopic("*.redismsg1.*"));
		container.addMessageListener(listenerAdapter2(receiver), new PatternTopic("*.redismsg2.*"));
		return container;
	}

	@Bean
	public MessageListenerAdapter listenerAdapter1(MessageReceiver receiver) {
		return new MessageListenerAdapter(messageReceiver(), "receiveMessage1");
	}
	@Bean
	public MessageListenerAdapter listenerAdapter2(MessageReceiver receiver) {
		return new MessageListenerAdapter(messageReceiver(), "receiveMessage2");
	}

	@Bean
	public MessageReceiver messageReceiver() {
		return new MessageReceiver();
	}

	@Bean
	public ObjectMapper ojectMapper() {
		return new ObjectMapper();
	}

}
