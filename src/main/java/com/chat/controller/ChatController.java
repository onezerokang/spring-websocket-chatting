package com.chat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate template;

    @GetMapping("/chat")
    public String chatGET() {
        System.out.println("@ChatController, chat GET()");
        return "chat";
    }

    @MessageMapping("/send")
    public void sendMessage(String message) {
        template.convertAndSend("/topic/chat", message);
    }
}

