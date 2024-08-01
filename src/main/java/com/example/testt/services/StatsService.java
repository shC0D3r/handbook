package com.example.testt.services;

import com.example.testt.dto.ServiceStatsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stat")
public class StatsService {
    @Autowired
    private ServiceStatsDto serviceStats;

    @GetMapping
    public String getRequest() {
        List<String> result = serviceStats.getStats().entrySet().stream()
                .map(x -> new String(x.getKey() + ": " + x.getValue())).toList();
        return String.join("<br>", result);
    }
}
