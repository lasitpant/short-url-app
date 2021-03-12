package com.urlapp.urlapp.service;


import com.urlapp.urlapp.entity.Url;
import com.urlapp.urlapp.entity.UrlMapping;
import com.urlapp.urlapp.repository.UrlMappingRepository;
import com.urlapp.urlapp.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UrlShorteningService {

    private final UrlRepository urlRepository;

    private final KeyGenerationService keyGenerationService;

    private final UrlMappingRepository urlMappingRepository;

    public UrlShorteningService(UrlRepository urlRespository, KeyGenerationService keyGenerationService,
                                UrlMappingRepository urlMappingRepository){
        this.urlRepository = urlRespository;
        this.keyGenerationService = keyGenerationService;
        this.urlMappingRepository = urlMappingRepository;
    }


    public String publishShortUrl(Url url){
       String shortUrl =  this.keyGenerationService.getKey();
       this.createUrlMapping(url,shortUrl);
        urlRepository.save(url);
       this.keyGenerationService.generateKeys();
       this.keyGenerationService.updateState(shortUrl);
       return "http://localhost:8080/"+shortUrl;
    }

    public String getOriginalUrl(String shortUrl){
        String url = urlMappingRepository.findByShortUrl(shortUrl).getLongUrl();
        if (validateUrl(url)){
            System.out.println("Removing Prefix");
            return removePrefix(url);
        }
        return "http://"+url;
    }

    private void createUrlMapping(Url url, String shortUrl){
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setShortUrl(shortUrl);
        urlMapping.setLongUrl(url.getLongUrl());
        urlMappingRepository.save(urlMapping);
    }

    private boolean validateUrl(String url){
        String URL_REGEX = "^((https?|http))";

        Pattern p = Pattern.compile(URL_REGEX);
        Matcher m = p.matcher(url);//replace with string to compare
        if(m.find()) {
            System.out.println("String contains http");
            return true;
        }
        return false;
    }
    private String removePrefix(String url){
        url.replace("http://","").replace("https://","");
        return url;
    }
}
