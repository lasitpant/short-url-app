package com.urlapp.urlapp.repository;

import com.urlapp.urlapp.entity.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping, String> {
    UrlMapping findByShortUrl(String shortUrl);
}
