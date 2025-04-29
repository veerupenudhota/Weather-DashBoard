package com.pvb.springboot.veeru.weather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping(value = "/{path:[^\\.]*}")
    public String redirect() {
        // Forward to index.html so React can handle routing
        return "forward:/index.html";
    }
}