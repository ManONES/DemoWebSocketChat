package com.manycode.app.ws.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.manycode.app.ws.SpringBootWebsocketApplication;
public class ChatMessage {

	private static final Logger LOGGER = LogManager.getLogger(SpringBootWebsocketApplication.class);
	
	private String content;
	private String sender;
	private MessageType type;
	
	public enum MessageType{
		CHAT,LEAVE,JOIN
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}
	
	
}
