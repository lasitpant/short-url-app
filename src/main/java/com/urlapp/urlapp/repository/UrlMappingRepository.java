package com.urlapp.urlapp.repository;

import com.urlapp.urlapp.entity.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlMappingRepository extends JpaRepository<UrlMapping, String> {
    UrlMapping findByShortUrl(String shortUrl);
}
