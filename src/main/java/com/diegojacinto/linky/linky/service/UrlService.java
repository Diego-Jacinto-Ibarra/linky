package com.diegojacinto.linky.linky.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegojacinto.linky.linky.model.entity.Url;
import com.diegojacinto.linky.linky.repository.UrlRepository;

@Service
public class UrlService {
    @Autowired
    private UrlRepository urlRepository;

    public String shortenUrl(String url) {
        if (urlRepository.existsByOriginalUrl(url)) {
            Url urlEntity = urlRepository.findByOriginalUrl(url);
            String resonseShortUrl = "https://diegojacinto.com/linky/" + urlEntity.getShortCode();
            return resonseShortUrl;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(url.getBytes());

            String base64 = Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
            String shortUrl = base64.substring(0, 8);
            Url urlEntity = new Url(url, shortUrl, true);
            urlRepository.save(urlEntity);

            String resonseShortUrl = "https://diegojacinto.com/linky/" + shortUrl;
            return resonseShortUrl;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating short URL", e);
        }
    }

    public String getOriginalUrl(String shortCode) {
        Url urlEntity = urlRepository.findByShortCode(shortCode);
        if (urlEntity != null) {
            return urlEntity.getOriginalUrl();
        }
        return null;
    }
}
