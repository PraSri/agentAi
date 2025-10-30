package com.example.aiagent;

import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AiagentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiagentApplication.class, args);
	}

    @Bean
    ChatMemoryRepository inMemoryChatMemory() {
        return new InMemoryChatMemoryRepository();
    }
}
