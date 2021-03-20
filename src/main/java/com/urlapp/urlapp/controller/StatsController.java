package com.urlapp.urlapp.controller;

import com.urlapp.urlapp.entity.DashboardStats;
import com.urlapp.urlapp.repository.StatisticRepository;
import com.urlapp.urlapp.service.StatisticService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController("/stats")
public class StatsController {

    private StatisticRepository statisticRepository;
    private StatisticService statisticService;
    public StatsController(StatisticRepository statisticRepository, StatisticService statisticService){
        this.statisticRepository = statisticRepository;
        this.statisticService = statisticService;
    }

    @GetMapping("/browser-stats")
    public List<DashboardStats> getDashBoardStats(@RequestBody File csv){
        statisticService.parseCsv(csv);
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
