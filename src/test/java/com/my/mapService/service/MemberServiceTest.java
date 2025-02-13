package com.my.mapService.service;

import com.my.mapService.dto.MemberDto;
import com.my.mapService.repository.MapMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
        car.setAddress("몰디브");
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
        assertThat(one.get().getName()).isEqualTo("카리나");
        assertThat(one.get().getAddress()).isEqualTo("몰디브");

    }
    //전체 리스트 출력
    @Test
    @DisplayName("전체 리스트 검색")
    public void findAll(){
        MemberDto kar = new MemberDto();
        kar.setName("카리나");
        kar.setAddress("몰디브");
        memberService.join(kar);
        MemberDto win = new MemberDto();
        win.setName("윈터");
        win.setAddress("제주");
        memberService.join(win);
        //When
        List<MemberDto> memberDtoList = memberService.findAll();
//        Then
        assertThat(memberDtoList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("아이디 받아서 삭제")
    public void deleteById() {
        MemberDto kar = new MemberDto();
        kar.setName("카리나");
        kar.setAddress("몰디브");
        memberService.join(kar);

        memberService.deleteById(1L);

        int size = memberService.findAll().size();
        assertThat(size).isEqualTo(0);
    }

    @Test
    @DisplayName("업데이트")
    public void update() {
        MemberDto kar = new MemberDto();
        kar.setName("카리나");
        kar.setAddress("몰디브");
        memberService.join(kar);
        MemberDto win = new MemberDto();
        win.setId(1L);
        win.setName("윈터");
        win.setAddress("제주");

        memberService.update(win);
        MemberDto result = memberService.findOne(1L).orElse(null);

        assertThat(result.getName()).isEqualTo("윈터");
        assertThat(result.getAddress()).isEqualTo("제주");
    }

}