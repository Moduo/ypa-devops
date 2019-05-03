package com.incentro.ypa.devops.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/hello")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("/hagoeie")
    public String groetnis(@RequestParam(name = "namme", required = false, defaultValue = "World") String namme, Model model) {
        model.addAttribute("namme", namme);
        return "hagoeie";
    }

}