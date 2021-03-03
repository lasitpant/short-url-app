package com.urlapp.urlapp.repository;
import com.urlapp.urlapp.entity.DashboardStats;
import com.urlapp.urlapp.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Long> {

    @Query("select new com.urlapp.urlapp.entity.DashboardStats(s.browser, count(s)) from Statistic s group by s.browser")
    List<DashboardStats> getBrowsers();

    @Query("select new com.urlapp.urlapp.entity.DashboardStats(s.deviceType, count(s)) from Statistic s group by s.deviceType")
    List<DashboardStats> getDevicesTypes();

    @Query("select new com.urlapp.urlapp.entity.DashboardStats(s.operatingSystem, count(s)) from Statistic s group by s.operatingSystem")
    List<DashboardStats> getOperatingSystems();
}
