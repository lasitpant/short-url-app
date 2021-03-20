package com.urlapp.urlapp.service;

import com.urlapp.urlapp.entity.Statistic;
import com.urlapp.urlapp.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import java.io.File;
import java.util.Map;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.stereotype.Service;

@Service
public class StatisticService {


    private StatisticRepository repository;

    public StatisticService(StatisticRepository repository){
        this.repository = repository;
    }

    public Statistic create(Statistic statistic) {
        statistic.setId(null);
        return repository.save(statistic);
    }

    public Statistic mapFrom(Map<String, String> headers, String url) {

        String userAgentString = headers.get(HttpHeaders.USER_AGENT.toLowerCase());

        userAgentString = userAgentString.replaceAll("[\n|\r|\t]", "_");



        UserAgent agent = UserAgent.parseUserAgentString(userAgentString);
        String deviceType = agent.getOperatingSystem().getDeviceType().getName();
        String browser = agent.getBrowser().getName();
        String operatingSystem = agent.getOperatingSystem().getName();

        return new Statistic(browser, deviceType, operatingSystem, url);
    }

    public void parseCsv(File file){
        
    }

}
