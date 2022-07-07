package com.ambulance.parsingApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MainController {

    @Autowired
    private AmbulanceEntityRepo ambulanceEntityRepo;

    @GetMapping
    public String main(Model model) throws Exception {

        filter("2020", model);

        return "index";
    }

    @GetMapping("start")
    public String start(Model model) throws Exception {

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

        return "index";
    }

    @PostMapping
    public String filter(@RequestParam String callDate, Model model) throws Exception {

        int[] values = new int[12];
        for (int i = 0; i < 12; i++)
            values[i] = ambulanceEntityRepo.countAllByCallDateBetween(new SimpleDateFormat(
                            "dd.MM.yyyy", Locale.ENGLISH).parse("01." + (i+1) + "." + callDate),
                    new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse("31." + (i+1) + "." + callDate));
        model.addAttribute("values", List.of(
                List.of("Январь", values[0]),
                List.of("Февраль", values[1]),
                List.of("Март", values[2]),
                List.of("Апрель", values[3]),
                List.of("Май", values[4]),
                List.of("Июнь", values[5]),
                List.of("Июль", values[6]),
                List.of("Август", values[7]),
                List.of("Сентябрь", values[8]),
                List.of("Октябрь", values[9]),
                List.of("Ноябрь", values[10]),
                List.of("Декабрь", values[11])
        ));
        model.addAttribute("yearIn", callDate);

        AmbulanceAnalysis analysis = new AmbulanceAnalysis();
        analysis.forecast(callDate, ambulanceEntityRepo);

        return "index";
    }
}