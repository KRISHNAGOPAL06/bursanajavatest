package com.example.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.*;

@Service
public class OpenRouterService {

    // OpenRouter API URL and your key
    private static final String API_URL = "https://openrouter.ai/api/v1/chat/completions";
    private static final String API_KEY = "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";

    public String getAIResponse(String prompt) {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();

        // Request body
        Map<String, Object> body = new HashMap<>();
        body.put("model", "mistralai/mistral-7b-instruct");
        body.put("temperature", 0.8);
        body.put("max_tokens", 350);
        body.put("messages", new Object[]{
            Map.of("role","user","content",prompt)
        });

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + API_KEY);

        HttpEntity<Map<String,Object>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(API_URL, request, String.class);
            JsonNode jsonNode = mapper.readTree(response.getBody());
            return jsonNode.get("choices").get(0).get("message").get("content").asText();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
