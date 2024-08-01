package com.example.testt.configuration;

import com.example.testt.dto.ServiceStatsDto;
import com.example.testt.dto.handbookString;
import com.opencsv.CSVReader;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class HandbookConfig {

    @Bean
    public List<handbookString> handbook() {
        return getHandbook();
    }

    @Bean
    public ServiceStatsDto serviceStats(){
        return new ServiceStatsDto(new HashMap<>());
    }


    @SneakyThrows
    private List<handbookString> getHandbook() {
        List<handbookString> handbookStringList = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader("D:\\tets\\MyCSV.csv"))) {
            String[] lineInArray;
            Integer i = 0;
            while ((lineInArray = reader.readNext()) != null) {
                String text = lineInArray[i];
                String[] splitted = text.split(";");
                handbookStringList.add(new handbookString(
                        splitted[3].replaceAll("\"", ""),
                        splitted[2].replaceAll("\"", ""),
                        splitted [0].replaceAll("\"", ""),
                        splitted[1].replaceAll("\"", "")));
            }
        }
        return handbookStringList;
    }
}
