package com.my.mapService.controller;

import com.my.mapService.dto.UserDto;
import com.my.mapService.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/signUp")
    public String signUp(Model model) {
        model.addAttribute("user", new UserDto());
        return "users/signUp";
    }

    @PostMapping("/signUp")
    public String signup(UserDto userDto) {
        service.SignIn(userDto);
        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login(UserDto userDto ,Model model) {
        List<UserDto> userList = service.findAll();
        System.out.println(userDto);
        model.addAttribute("userList", userList);
        return "users/login";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        List<UserDto> userList = service.findAll();
        model.addAttribute("userList", userList);
        return "users/admin";
    }

    @GetMapping("/update/{userId}")
    public String update(@PathVariable("userId") String userId, Model model) {
        model.addAttribute("user", service.findById(userId));
        return "users/update";
    }

    @PostMapping("/update")
    public String upDate(UserDto userDto) {
        System.out.println(userDto);
        service.update(userDto);
        return "redirect:/users/admin";
    }

    @GetMapping("/delete/{userId}")
    public String deleteId(@PathVariable("userId") String userId) {
        service.deleteById(userId);
        return "redirect:/users/admin";
    }
}
