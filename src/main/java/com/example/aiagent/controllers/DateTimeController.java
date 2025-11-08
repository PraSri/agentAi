package com.example.aiagent.controllers;

import com.example.aiagent.tools.DateTimeTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/datetime")
public class DateTimeController {

    private final ChatClient chatClient;

    @Autowired
    private DateTimeTools dateTimeTools;

    public DateTimeController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.defaultSystem("""
        You can use the provided tools to take actions on behalf of the user.
        When the user asks to set or schedule an alarm, first call GET_DATE_TIME to get the current time,
        calculate the alarm time (e.g. "10 minutes from now"), and then call SET_ALARM with that computed time.
    """).build();
    }

    // example prompt -  "Can you set an alarm 10 minutes from now?"
    @GetMapping
    String getCurrentDateTime(@RequestParam(defaultValue = "Give current date time") String prompt) {
        PromptTemplate pt = new PromptTemplate("""
                {prompt}
                """);
        Prompt p = pt.create(Map.of("prompt", prompt));
        System.out.print("prompt - " + p);
        return chatClient.
                prompt(p).tools(dateTimeTools).call().content();
    }




}
