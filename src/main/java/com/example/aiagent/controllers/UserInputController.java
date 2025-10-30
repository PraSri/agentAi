package com.example.aiagent.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class UserInputController {

    private final ChatClient chatClient;

    public UserInputController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping
    String generation(@RequestParam String userInput) {
        System.out.println("Calling the chatClient prompt");
        return this.chatClient.prompt()
                .user(userInput)
                .call()
                .content();
    }

}
