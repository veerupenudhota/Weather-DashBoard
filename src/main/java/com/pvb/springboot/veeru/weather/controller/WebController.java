package com.pvb.springboot.veeru.weather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class WebController {

    @GetMapping("/")
    public RedirectView index() {
        return new RedirectView("/index.html");
    }
}
