package com.manycode.app.ws;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootWebsocketApplication {
	
	private static final Logger LOGGER = LogManager.getLogger(SpringBootWebsocketApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebsocketApplication.class, args);
		LOGGER.info("....INICIA CHAT MANY SpringBootWebsocketApplication");
	}

}
