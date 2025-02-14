package com.my.mapService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListHome {
    @GetMapping("/")
    public String listMain() {
        return "listhome";
    }

}
