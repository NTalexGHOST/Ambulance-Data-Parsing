package com.ambulance.parsingApp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    @GetMapping("/")
    public String home(Map<String, Object> model) {

        // Данный метод вызывается при заходе на сайт (localhost:8080)
        // Здесь нужно будет разместить алгоритм парсинга данных, добавление их в бд и обновление если нужно
        
        List<AmbulanceEntity> test = new ArrayList<>();
        ExcelParser parser = new ExcelParser();
        test = parser.getAmbulanceData("src/data/2022/1.xls");
        
        return "home";
    }

    @PostMapping
    public String filter(@RequestParam int year, Map<String, Object> model) {

        // Данный метод будет вызываться по введению пользователем интересующего года и нажатия на кнопку
        // Также метод будет возвращать (!!!через model.put, а не return!!!) уже отсортированные по годы данные для графика
        // Здесь и нужно разместить алгоритм для сортировки

        List<String> values = new ArrayList<>();
        model.put("values", values);
        return "home";
    }
}