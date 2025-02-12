package com.my.mapService.controller;

import com.my.mapService.dto.Hello;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "Welcome SpringBoot!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String hellomvc(
            @RequestParam(name = "age" ,required = false, defaultValue = "30")Integer age,
            @RequestParam(name = "name", required = false, defaultValue = "씨게가자") String name,
            Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "hello-template";
    }
//    http://localhost:8080/api-string?name=카리나
//    API타입
    @GetMapping("api-string")
    @ResponseBody
    public String apiString(@RequestParam("name")String name, Model model){

        return "hello"+name;
    }
//    이케 날리면 JSON으로 나온다잉
    @GetMapping("api-object")
    @ResponseBody
    public Hello helloApi(@RequestParam("name")String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
}
