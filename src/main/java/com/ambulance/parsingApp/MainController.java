package com.ambulance.parsingApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private AmbulanceEntityRepo ambulanceEntityRepo;

    @GetMapping("/")
    public String home(Map<String, Object> model) {
        return "home";
    }

    @GetMapping("/start")
    public String start(Map<String, Object> model) throws IOException {

        // Данный метод вызывается при заходе на сайт (localhost:8080)
        ExcelParser parser = new ExcelParser();
        File directory = new File("src/data");
        for (File item1 : directory.listFiles())
            for (File item2 : item1.listFiles()) {
                System.out.print(new Date().toString() + " Начата проверка файла по пути " + item2.getPath() + "\n");
                parser.getAmbulanceData(item2.getPath(), ambulanceEntityRepo);
                System.out.print(new Date().toString() + " Проверка файла завершена\n\n");
            }

        return "home";
    }

    @PostMapping
    public String filter(@RequestParam String year, Map<String, Object> model) {

        // Данный метод будет вызываться по введению пользователем интересующего года и нажатия на кнопку

        Optional<AmbulanceEntity> values = ambulanceEntityRepo.findById("9420(1143)");
        model.put("values", values);

        return "home";
    }
}