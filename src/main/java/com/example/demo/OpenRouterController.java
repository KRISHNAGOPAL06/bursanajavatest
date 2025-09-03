package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class OpenRouterController {

    @Autowired
    private OpenRouterService openRouterService;

    @PostMapping("/chat")
    public String chat(@RequestBody Map<String,String> request){
        String prompt = request.get("prompt");
        return openRouterService.getAIResponse(prompt);
    }
}
