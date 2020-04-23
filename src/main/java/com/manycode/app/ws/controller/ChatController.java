package com.manycode.app.ws.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.manycode.app.ws.service.MessageChatService;
import com.manycode.app.ws.SpringBootWebsocketApplication;
import com.manycode.app.ws.model.ChatMessage;
import com.manycode.app.ws.model.MessageChat;

@Controller
public class ChatController {

	@Autowired
	private MessageChatService messagechatService; 
	
	private static final Logger LOGGER = LogManager.getLogger(SpringBootWebsocketApplication.class);
	
	@MessageMapping("/chat.register")
	@SendTo("/topic/public")
	public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccesor) {
		LOGGER.info("....ChatMessage register > chatMessage.getSender: " + chatMessage.getSender());		
		LOGGER.info("....ChatMessage register > chatMessage.getContent: " + chatMessage.getContent());		
		LOGGER.info("....ChatMessage register > chatMessage.getType: " + chatMessage.getType());
		
		MessageChat messagechat = new MessageChat();

		messagechat.setMessagechat("Seder:" + chatMessage.getSender() + ". Content:" + chatMessage.getContent());
		messagechat.setMessagetype("" +chatMessage.getType());		
		
		headerAccesor.getSessionAttributes().put("username",chatMessage.getSender());
		
		LOGGER.info("....Guarda mensaje en  BD.");
		MessageChat messagechatCreate = messagechatService.createMessageChat(messagechat);
		LOGGER.info("....Fin Guarda mensaje en  BD.");
		
		
		return chatMessage;
	}
	
	@MessageMapping("/chat.send")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
				
		LOGGER.info("....ChatMessage sendMessage.");
		LOGGER.info("....ChatMessage sendMessage > chatMessage.getSender: " + chatMessage.getSender());		
		LOGGER.info("....ChatMessage sendMessage > chatMessage.getContent: " + chatMessage.getContent());		
		LOGGER.info("....ChatMessage sendMessage > chatMessage.getType: " + chatMessage.getType());
		
		MessageChat messagechat = new MessageChat();
		
		messagechat.setMessagechat("Seder:" + chatMessage.getSender() + ". Content:" + chatMessage.getContent());
		messagechat.setMessagetype("" +chatMessage.getType());
		
		LOGGER.info("....Guarda mensaje en  BD.");
		MessageChat messagechatCreate = messagechatService.createMessageChat(messagechat);
		LOGGER.info("....Fin Guarda mensaje en  BD.");
		
		return chatMessage;
	}
	
	
	@GetMapping("/listar")
	public String greeting(Model model) {
				
		model.addAttribute("mensajes",messagechatService.listAllMessageChat());
		
		return "greeting";
	}
		
}

