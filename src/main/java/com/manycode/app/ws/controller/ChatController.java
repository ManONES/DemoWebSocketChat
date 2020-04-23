package com.manycode.app.ws.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.manycode.app.ws.service.MessageChatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manycode.app.ws.controller.ErrorMensaje;
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

		messagechat.setMessagesender("Seder:" + chatMessage.getSender());
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
		
		messagechat.setMessagesender("Seder:" + chatMessage.getSender());
		messagechat.setMessagechat("Content:" + chatMessage.getContent());
		messagechat.setMessagetype("" +chatMessage.getType());
		
		LOGGER.info("....Guarda mensaje en  BD.");
		MessageChat messagechatCreate = messagechatService.createMessageChat(messagechat);
		LOGGER.info("....Fin Guarda mensaje en  BD.");
		
		return chatMessage;
	}
	
	
	
	@GetMapping("/listar")
	public String greeting(Model model) {
		LOGGER.info("....Lista TODO mensaje en  BD.");		
		model.addAttribute("mensajes",messagechatService.listAllMessageChat());
		LOGGER.info("....Fin Lista TODO mensaje en  BD.");
		
		model.addAttribute("error","No hay error!");
		return "greeting";
	}
	
	@GetMapping(value = "/listar/id/{id}")
	public String greeting(@PathVariable("id") Long id, Model model) {

		LOGGER.info("....Lista por ID mensaje en  BD.");		
		MessageChat messagechat = messagechatService.getMessageChat(id);
		LOGGER.info("....Fin Lista por ID mensaje en  BD.");
	
		if (null==messagechat) {
			model.addAttribute("error",ResponseEntity.notFound().build()); 
			return "greeting";   
		}				
		
		model.addAttribute("error","No hay error!"); 
		model.addAttribute("mensajes",messagechatService.getMessageChat(id));
		
		return "greeting";
	}
	
	@GetMapping(value = "/listar/idid/{id}")
	public ResponseEntity<MessageChat> getMesageChat(@PathVariable("id") Long id) {
		LOGGER.info("....ResponseEntity<MessageChat> id: " + id);
		MessageChat messagechat = messagechatService.getMessageChat(id);
		if (null==messagechat) {
			return ResponseEntity.notFound().build();
		}		
		return ResponseEntity.ok(messagechat);
	}
	
	

	private String formatMessage(BindingResult result) {
		List<Map<String,String>> errors = result.getFieldErrors().stream()
				.map(err ->{
					Map<String,String> error = new HashMap<>();
					error.put(err.getField(), err.getDefaultMessage());
					return error;
					
				}).collect(Collectors.toList());
		ErrorMensaje errorMensaje = ErrorMensaje.builder()
				.code("01")
				.messages(errors).build();
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString= "";
		try {
			jsonString = mapper.writeValueAsString(errorMensaje);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return jsonString;
				
	}
	
}

