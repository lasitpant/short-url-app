package com.urlapp.urlapp.controller;

import com.urlapp.urlapp.entity.DashboardStats;
import com.urlapp.urlapp.repository.StatisticRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController("/stats")
public class StatsController {

    private StatisticRepository statisticRepository;

    public StatsController(StatisticRepository statisticRepository){
        this.statisticRepository = statisticRepository;
    }

    @GetMapping("/browser-stats")
    public List<DashboardStats> getDashBoardStats(){
        return statisticRepository.getBrowsers();

    }

    @GetMapping("/device-stats")
    public List<DashboardStats> getDashDeviceStats(){
        return statisticRepository.getDevicesTypes();

    }

    @GetMapping("/os-stats")
    public List<DashboardStats> getDashOsStats(){
        return statisticRepository.getOperatingSystems();

    }
}
