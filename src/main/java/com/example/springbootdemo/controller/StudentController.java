package com.example.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
public class StudentController {

    @RequestMapping(value = "hello")
    public String helloMethod(Model model){
        model.addAttribute("now", LocalDateTime.now().toString());
        return "index";
    }

}
