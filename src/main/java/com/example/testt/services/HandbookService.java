package com.example.testt.services;

import com.example.testt.configuration.HandbookConfig;
import com.example.testt.dto.ServiceStatsDto;
import com.example.testt.dto.handbookString;
import com.opencsv.CSVReader;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/mkb10")
public class HandbookService {
    @Autowired
    private List<handbookString> handbook;

    @Autowired
    private ServiceStatsDto serviceStats;

    @GetMapping("/{req}")
    public String getRequest(@PathVariable String req) {
        List<handbookString> handbookStringList = handbook.stream()
                .filter(s -> s.getS_name().toUpperCase(Locale.ROOT).contains(req.toUpperCase(Locale.ROOT)) ||
                        s.getIcd_10().toUpperCase(Locale.ROOT).contains(req.toUpperCase(Locale.ROOT)))
                .toList();
        List<String> resultList = handbookStringList.stream().map(
                x -> x.getIcd_10() + ", " + x.getS_name() + "<br>" ).toList();

        serviceStats.addStat(req);
        return String.join("<br>", resultList);
    }
}
