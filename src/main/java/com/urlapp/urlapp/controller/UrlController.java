package com.urlapp.urlapp.controller;

import com.urlapp.urlapp.entity.Url;
import com.urlapp.urlapp.service.UrlShorteningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

@RestController
public class UrlController {

    private final UrlShorteningService urlShorteningService;

    public UrlController(UrlShorteningService urlShorteningService){
        this.urlShorteningService = urlShorteningService;
    }

    @PostMapping("generate-short-link")
    public String generateShortUrl(@RequestBody Url url){
        url.setCreatedOn(new Date());
        return urlShorteningService.publishShortUrl(url);

    }

    @GetMapping("/{shortUrl}")
    public RedirectView getAndRedirect(@PathVariable String shortUrl){
        String redirectUrl = urlShorteningService.getOriginalUrl(shortUrl);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://" + redirectUrl);
        return redirectView;
    }
}
