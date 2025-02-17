package com.my.mapService.controller;

import com.my.mapService.dto.UserDto;
import com.my.mapService.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        model.addAttribute("user", userDto);
        return "users/login";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        List<UserDto> userList = service.findAll();
        model.addAttribute("userList", userList);
        return "users/admin";
    }

    @GetMapping("/update/{userId}")
    public String update(@PathVariable("userId") String userId, Model model , HttpSession session) {
        //세션정보를 얻어서 DTO에 담는다.
        UserDto sessionUser = (UserDto) session.getAttribute("sessionInfo");
        //로그인 상태인지 아닌지를 비교판단
        if (ObjectUtils.isEmpty(sessionUser)) {
            //로그아웃상태
            return "users/login";
        } else {
            //로그인 상태
            Optional<UserDto> user = service.findById(userId);
            model.addAttribute("user", user);
            return "users/update";
        }
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
    @GetMapping("/myPage")
    public String myPage () {
        return "users/myPage";
    }
}
