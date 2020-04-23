package com.manycode.app.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.manycode.app.ws.model.MessageChat;

public interface MessageChatRepository extends JpaRepository<MessageChat, Long> {

}
