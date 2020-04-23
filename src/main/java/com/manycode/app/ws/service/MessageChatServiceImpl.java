package com.manycode.app.ws.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.manycode.app.ws.model.MessageChat;
import com.manycode.app.ws.repository.MessageChatRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageChatServiceImpl implements MessageChatService{

	private final MessageChatRepository messagechatRepository;
	
	@Override
	public MessageChat createMessageChat(MessageChat messagechat) {
		messagechat.setStatus("CREATED");
		messagechat.setCreateAt(new Date());
		return messagechatRepository.save(messagechat);
	}
	
	@Override
	public List<MessageChat> listAllMessageChat() {
		// TODO Auto-generated method stub
		return messagechatRepository.findAll();
	}
	

}
