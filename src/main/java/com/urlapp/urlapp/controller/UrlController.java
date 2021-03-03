package com.urlapp.urlapp.controller;

import com.urlapp.urlapp.entity.Statistic;
import com.urlapp.urlapp.entity.Url;
import com.urlapp.urlapp.service.StatisticService;
import com.urlapp.urlapp.service.UrlShorteningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
public class UrlController {

    private final UrlShorteningService urlShorteningService;

    private StatisticService statisticService;

    public UrlController(UrlShorteningService urlShorteningService, StatisticService statisticService){
        this.urlShorteningService = urlShorteningService;
        this.statisticService = statisticService;
    }

    @PostMapping("generate-short-link")
    public Map<String, String> generateShortUrl(@RequestBody Url url){
        String shortenUrl = urlShorteningService.publishShortUrl(url);
        Map<String,String> response = new HashMap<String, String>();
        response.put("shortUrl",shortenUrl);
        return response;
    }

    @GetMapping("/{shortUrl}")
    public RedirectView getAndRedirect(@PathVariable String shortUrl, @RequestHeader Map<String, String> headersMap){

        Statistic statistic = statisticService.mapFrom(headersMap,shortUrl);
        statisticService.create(statistic);
        try {
            String redirectUrl = urlShorteningService.getOriginalUrl(shortUrl);
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("http://" + redirectUrl);
            return redirectView;
        }
        catch (Exception e){
            throw new EntityNotFoundException();
        }


    }
}
