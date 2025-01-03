package com.diegojacinto.linky.linky.controller;

import com.diegojacinto.linky.linky.service.UrlService;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortCode) {
        try {
            String originalUrl = urlService.getOriginalUrl(shortCode);
            System.out.println("originalUrl: " + originalUrl);
            if (originalUrl != null) {
                return ResponseEntity.status(HttpStatus.FOUND)
                        .location(URI.create(originalUrl))
                        .build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}