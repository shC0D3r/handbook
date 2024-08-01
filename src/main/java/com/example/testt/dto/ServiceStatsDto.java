package com.example.testt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceStatsDto {
    private Map<String, Integer> stats;

    public void addStat(String name){
        if (Objects.nonNull(stats.get(name))) {
            stats.replace(name, stats.get(name) + 1);
        } else {
            stats.put(name, 1);
        }
    }

}
