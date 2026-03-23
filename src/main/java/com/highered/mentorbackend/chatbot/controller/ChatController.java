package com.highered.mentorbackend.chatbot.controller;

import com.highered.mentorbackend.chatbot.service.GeminiService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ChatController {

    private final GeminiService geminiService;

    public ChatController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @GetMapping(value = "/api/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamChat(@RequestParam String prompt) {

        System.out.println("📩 Prompt received: " + prompt);

        SseEmitter emitter = new SseEmitter(0L);

        new Thread(() -> {
            try {
                String reply = geminiService.getGeminiReply(prompt);
                System.out.println("🤖 Gemini reply: " + reply);

                emitter.send(SseEmitter.event()
                        .name("message")
                        .data(reply));

                emitter.complete();
            } catch (Exception e) {
                e.printStackTrace();
                emitter.completeWithError(e);
            }
        }).start();

        return emitter;
    }
}