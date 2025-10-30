package com.example.aiagent.controllers;

import com.example.aiagent.models.Person;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final ChatClient chatClient;

    @Autowired
    private ChatMemoryRepository chatMemoryRepo;

    private final ChatMemory chatMemory = MessageWindowChatMemory
            .builder()
            .chatMemoryRepository(chatMemoryRepo)
            .maxMessages(10)
            .build();

    public PersonController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    // Prompting and structured output
    @GetMapping
    List<Person> findAll() {
        PromptTemplate pt = new PromptTemplate("""
                Return a current list of 10 famous persons if exists or generate a new list with random values.
                Each object should contain an auto-incremented id field.
                Do not include any explanations or additional text.
                """);

        return this.chatClient.prompt(pt.create())
                .call()
                .entity(new ParameterizedTypeReference<>() {});
    }

    // Advisors and Chat Memory



}
