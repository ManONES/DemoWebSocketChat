package com.manycode.app.ws.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.manycode.app.ws.SpringBootWebsocketApplication;
import com.manycode.app.ws.model.ChatMessage;

@Controller
public class ChatController {

	private static final Logger LOGGER = LogManager.getLogger(SpringBootWebsocketApplication.class);
	
	@MessageMapping("/chat.register")
	@SendTo("/topic/public")
	public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccesor) {
		headerAccesor.getSessionAttributes().put("username",chatMessage.getSender());
		return chatMessage;
	}
	
	@MessageMapping("/chat.send")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
		return chatMessage;
	}
	
}
