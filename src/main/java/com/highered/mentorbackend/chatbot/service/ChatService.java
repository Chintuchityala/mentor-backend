package com.highered.mentorbackend.chatbot.service;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final GeminiService geminiService;

    public ChatService(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    public String getReply(String userMessage) {

        String prompt =
                "You are an AI career guidance assistant for Indian students. " +
                "Answer clearly and simply.\n\nUser question: "
                + userMessage;

        return geminiService.getGeminiReply(prompt);
    }
}