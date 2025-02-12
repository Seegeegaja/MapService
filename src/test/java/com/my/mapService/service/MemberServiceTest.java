package com.my.mapService.service;

import com.my.mapService.dto.MemberDto;
import com.my.mapService.repository.MapMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MapMemberRepository repository = new MapMemberRepository();
    MemberService memberService = new MemberService(repository);

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }
    @Test
    void 회원가입(){
        //Given
        MemberDto see = new MemberDto();
        see.setName("씨게");
        see.setAddress("제주");
        //When
        Long chill = memberService.join(see);
        //Then
        MemberDto findId = memberService.findOne(chill).orElse(null);
        assertThat(findId.getName()).isEqualTo(see.getName());

    }

    @Test
    public void 중복회원검서() {
        //Given
        MemberDto kar = new MemberDto();
        kar.setName("카리나");
        kar.setAddress("몰디브");
        memberService.join(kar);
        MemberDto win = new MemberDto();
        win.setName("윈터");
        win.setAddress("제주");
        memberService.join(win);
        //When
        MemberDto car = new MemberDto();
        car.setName("카리나");
        car.setAddress("뉴욕");
        Long chill = memberService.join(car);
        //then
        assertThat(chill).isEqualTo(-1L);


    }

    @Test
    public void findOne() {
        MemberDto kar = new MemberDto();
        kar.setName("카리나");
        kar.setAddress("몰디브");

        Long findId = memberService.join(kar);

        Optional<MemberDto> one = memberService.findOne(1L);
        MemberDto qwe = memberService.findOne(findId).orElse(null);
        assertThat(one.get().getName()).isEqualTo(kar.getName());

    }

}