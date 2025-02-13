package com.my.mapService.controller;

import com.my.mapService.dto.MemberDto;
import com.my.mapService.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.List;

@Controller
@RequestMapping("/members")
//@RequiredArgsConstructor
public class MemberController {

//    private final MemberService service;
        private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }


//    @Autowired
//    MemberService service;

    @GetMapping("")
    public String user(Model model) {
        //전체 리스트 가져오기
        List<MemberDto> memberList = service.findAll();
        model.addAttribute("memberList",memberList);
        return "members/memberList";
    }


    @GetMapping("/new")
    public String qwe(Model model) {
        model.addAttribute("member", new MemberDto());
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
    public String createMember( MemberDto memberDto) {
        service.join(memberDto);
        return "redirect:/members";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")Long id, Model model) {
        System.out.println("id : " +id);
        service.deleteById(id);
        return "redirect:/members";
    }

    @GetMapping("/update/{id}")
    public String upDate(@PathVariable("id")Long id ,Model model) {
        //1.아이디로 검색 후 member를 수정 폼에 전달 해서 뿌려줌
        model.addAttribute("member", service.findOne(id));
        return "members/updateMemberForm";
    }
    @PostMapping("/update")
    public String update(MemberDto memberDto) {
        System.out.println(memberDto);
        service.update(memberDto);
        return "redirect:/members";
    }

//    updateMemberForm
//    @GetMapping("/")
//    public String home(){
//        return "home";
//    }
}
