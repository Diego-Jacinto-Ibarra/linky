package com.diegojacinto.linky.linky.controller;

import com.diegojacinto.linky.linky.service.UrlService;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/urls/shorten")
    @ResponseBody
    public ResponseEntity<Map<String, String>> shortenUrl(@RequestBody String url) {
        Map<String, String> response = new HashMap<>();
        try {
            String decodedUrl = URLDecoder.decode(url, StandardCharsets.UTF_8.name());
            String shortenedUrl = urlService.shortenUrl(decodedUrl);
            response.put("shortenedUrl", shortenedUrl);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("error", "Failed to shorten URL");
        }
        return ResponseEntity.ok(response);
    }
}