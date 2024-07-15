package com.example.coffeeshop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CofeeshopController {

    @GetMapping("/")
    public String getHome() {
        return "index";
    }
}
