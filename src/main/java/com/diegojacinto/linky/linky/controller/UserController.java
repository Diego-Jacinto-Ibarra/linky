package com.diegojacinto.linky.linky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class UserController {


    @GetMapping("/dashboard")
    public String dashboard() {

        return "dashboard";
    }
}
