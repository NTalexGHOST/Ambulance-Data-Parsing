package com.ambulance.parsingApp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @GetMapping
    public String main(Map<String, Object> model) {
        model.put("name", "home");
        return "MainPage";
    }

}