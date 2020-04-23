package com.manycode.app.ws.service;


import java.util.List;

import com.manycode.app.ws.model.MessageChat;

public interface MessageChatService {

	public MessageChat createMessageChat(MessageChat messagechat);
	public MessageChat getMessageChat(Long id);
	public List<MessageChat> listAllMessageChat();
	
}
