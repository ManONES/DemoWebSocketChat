package com.manycode.app.ws.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.manycode.app.ws.SpringBootWebsocketApplication;

@Configuration
@EnableWebSocketMessageBroker
public class WsConfig implements WebSocketMessageBrokerConfigurer{
	
	private static final Logger LOGGER = LogManager.getLogger(SpringBootWebsocketApplication.class);

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		//WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
		registry.addEndpoint("/manycode").withSockJS();
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//WebSocketMessageBrokerConfigurer.super.configureMessageBroker(registry);
		registry.enableSimpleBroker("/topic");
		registry.setApplicationDestinationPrefixes("/app");
	}
	
}
