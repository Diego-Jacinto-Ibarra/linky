package com.diegojacinto.linky.linky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.diegojacinto.linky.linky.model.entity.Url;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Url findByShortCode(String shortCode);
    Url findByOriginalUrl(String originalUrl);
    boolean existsByOriginalUrl(String originalUrl);
}