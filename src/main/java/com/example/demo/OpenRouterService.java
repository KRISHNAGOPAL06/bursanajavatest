package com.example.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.*;

@Service
public class OpenRouterService {

    private static final String API_URL = "https://openrouter.ai/api/v1/chat/completions";
    private static final String apiKey = System.getenv("API_KEY"); // Read from Render env

    public String getAIResponse(String prompt) {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> body = new HashMap<>();
        body.put("model", "mistralai/mistral-7b-instruct");
        body.put("temperature", 0.8);
        body.put("max_tokens", 350);
        body.put("messages", new Object[]{
                Map.of("role", "user", "content", prompt)
        });

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

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
