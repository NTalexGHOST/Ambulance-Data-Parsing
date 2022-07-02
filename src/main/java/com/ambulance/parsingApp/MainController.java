package com.ambulance.parsingApp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    @GetMapping("/")
    public String main(Map<String, Object> model) {
        model.put("name", "home");
        return "MainPage";
    }
    @GetMapping("/test-data")
    public List<AmbulanceEntity> test (@RequestParam(value = "path", defaultValue = "src/data/2022/1.xls") String path) throws IOException {
        ExcelParser parser = new ExcelParser();
        return parser.getAmbulanceData(path);
    }
}