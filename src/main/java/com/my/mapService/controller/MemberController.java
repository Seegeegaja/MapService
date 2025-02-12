package com.my.mapService.controller;

import com.my.mapService.dto.MemberDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Member;

@Controller
@RequestMapping("/members")
public class MemberController {
    @GetMapping("")
    public String user() {
        //전체 리스트 가져오기
        return "members/memberList";
    }


    @GetMapping("/new")
    public String qwe() {
        //신규 회원 가입하기
        return "members/createMemberForm";
    }
    //    @PostMapping("/new")
//    public String createMember() {
//    회원가입창에서 보낸자료를
//    DTO로 받는다
//    2.받은 회원 정보를 서비스에 보내서 Map에 저장한다
//        return "redirect:갈곳의 URL적기";
//    }

    @PostMapping("/new")
    public String createMember(MemberDto memberDto) {
        System.out.println(memberDto);
        return "redirect:/members";
    }
//    @GetMapping("/")
//    public String home(){
//        return "home";
//    }
}
