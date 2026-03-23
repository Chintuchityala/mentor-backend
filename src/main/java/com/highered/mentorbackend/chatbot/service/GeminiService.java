package com.highered.mentorbackend.chatbot.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeminiService {

    @Value("${GEMINI_API_KEY}")
    private String apiKey;
    @Autowired


    private static final String GEMINI_URL =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=";

    // 🔥 Strong system prompt to control clarity + tables
    private static final String SYSTEM_PROMPT = """
        You are an AI assistant for a Higher Education Mentor application.

        Rules:
        1. Use very simple language.
        2. Avoid long paragraphs.
        3. For explanations, use bullet points.
        4. If the user asks for tabular or list data:
           - Return ONLY a markdown table
           - Use single | (pipe) symbol
           - Do NOT use || double pipes
           - Do NOT add explanation text before or after the table
        5. Keep answers easy for 10th or 12th class students.
        6. Do NOT use complex words.

        User Query:
        """;

    public String getGeminiReply(String userInput) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            // Combine system prompt + user input
            String finalPrompt = SYSTEM_PROMPT + userInput;

            // Escape user input safely
            String safePrompt = escapeJson(finalPrompt);

            // 🔥 Gemini request with low temperature for clarity
            String requestBody = """
            {
              "contents": [{
                "parts": [{
                  "text": "%s"
                }]
              }],
              "generationConfig": {
                "temperature": 0.3,
                "topP": 0.8
              }
            }
            """.formatted(safePrompt);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(
                    GEMINI_URL + apiKey,
                    entity,
                    String.class
            );

            System.out.println("🌐 Gemini raw response: " + response.getBody());

            JsonNode root = mapper.readTree(response.getBody());
            JsonNode textNode = root
                    .path("candidates")
                    .path(0)
                    .path("content")
                    .path("parts")
                    .path(0)
                    .path("text");

            if (textNode.isMissingNode() || textNode.asText().trim().isEmpty()) {
                return "Sorry, I couldn’t understand. Please try again.";
            }

            // 🔥 Clean + normalize response
            return normalizeResponse(textNode.asText());

        } catch (Exception e) {
            e.printStackTrace();
            return "Sorry, something went wrong while fetching the response.";
        }
    }

    // ✅ Escape JSON safely
    private String escapeJson(String text) {
        return text
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n");
    }



    // ✅ Normalize table + text output
    private String normalizeResponse(String text) {
        return text
                .replaceAll("\\|\\|", "|")     // remove double pipes
                .replaceAll("-{4,}", "---")    // fix table separators
                .replaceAll("\\n{3,}", "\n\n") // remove extra new lines
                .trim();
    }
}
