package com.urlapp.urlapp.service;


import com.urlapp.urlapp.entity.Url;
import com.urlapp.urlapp.entity.UrlMapping;
import com.urlapp.urlapp.repository.UrlMappingRepository;
import com.urlapp.urlapp.repository.UrlRespository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UrlShorteningService {

    private final UrlRespository urlRespository;

    private final KeyGenerationService keyGenerationService;

    private final UrlMappingRepository urlMappingRepository;

    public UrlShorteningService(UrlRespository urlRespository, KeyGenerationService keyGenerationService,
                                UrlMappingRepository urlMappingRepository){
        this.urlRespository = urlRespository;
        this.keyGenerationService = keyGenerationService;
        this.urlMappingRepository = urlMappingRepository;
    }


    public String publishShortUrl(Url url){
       String shortUrl =  this.keyGenerationService.getKey();
       this.createUrlMapping(url,shortUrl);
       urlRespository.save(url);
       this.keyGenerationService.generateKeys();
       this.keyGenerationService.updateState(shortUrl);
       return "http://localhost:8080/"+shortUrl;
    }

    public String getOriginalUrl(String shortUrl){
        return urlMappingRepository.findByShortUrl(shortUrl).getLongUrl();
    }

    private void createUrlMapping(Url url, String shortUrl){
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setShortUrl(shortUrl);
        urlMapping.setLongUrl(url.getLongUrl());
        urlMappingRepository.save(urlMapping);
    }
}
