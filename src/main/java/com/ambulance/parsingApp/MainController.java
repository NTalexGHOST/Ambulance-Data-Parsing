package com.ambulance.parsingApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.*;

@Controller
public class MainController {

    @Autowired
    private AmbulanceEntityRepo ambulanceEntityRepo;

    @GetMapping
    public String main(Map<String, Object> model) {

        // Данный метод вызывается при заходе на сайт (localhost:8080)
        Iterable<AmbulanceEntity> values = ambulanceEntityRepo.findAll();
        model.put("values", values);

        return "home";
    }

    @GetMapping("start")
    public String start(Map<String, Object> model) throws IOException {

        // Данный метод вызывается при заходе на сайт (localhost:8080/start) и начинает проверку всех файлов
        // Файл для записи названий файлов, которые уже были проверены раньше
        File checked = new File("src/checked.txt");
        List<String> checkedFiles = null;
        boolean newFileCondition = false;
        if (!checked.exists()) {
            checked.createNewFile();
            newFileCondition = true;
        } else {
            Scanner scanner = new Scanner(Paths.get(checked.getPath()), StandardCharsets.UTF_8.name());
            checkedFiles = Arrays.asList(scanner.useDelimiter("\\A").next().split("\r\n"));
            scanner.close();
        }
        ExcelParser parser = new ExcelParser();
        File directory = new File("src/data");
        FileWriter writer = new FileWriter(checked, true);
        for (File item1 : directory.listFiles())
            for (File item2 : item1.listFiles()) {
                String temp = item2.getName().split("\\.")[0];
                System.out.print(new Date().toString() + " Начата проверка файла по пути " + item2.getPath() + "\n");
                if (newFileCondition || !checkedFiles.contains(item1.getName() + "_" + temp)) {
                    parser.getAmbulanceData(item2.getPath(), ambulanceEntityRepo);
                    writer.write(item1.getName() + "_" + temp + "\r\n");
                    writer.flush();
                }
                System.out.print(new Date().toString() + " Проверка файла завершена\n\n");
            }
        writer.close();

        return "home";
    }

    @PostMapping("filter")
    public String filter(@RequestParam java.util.Date callDate, Map<String, Object> model) {

        List<AmbulanceEntity> values = ambulanceEntityRepo.findByCallDate(callDate);
        model.put("values", values);

        return "home";
    }
}