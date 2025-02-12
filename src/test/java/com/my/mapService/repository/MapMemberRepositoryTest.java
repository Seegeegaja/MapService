package com.my.mapService.repository;

import com.my.mapService.dto.MemberDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MapMemberRepositoryTest {
    MapMemberRepository repository = new MapMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    @DisplayName("저장하기")
    void save() {
//        Given
        MemberDto memberDto = new MemberDto();
        memberDto.setName("장원영");
        memberDto.setAddress("서울");

//        When
        MemberDto result = repository.save(memberDto);
//        Then
        MemberDto saveMember = new MemberDto();
        Long saveId = 1L;
        //org.junit.juipter.api
        Assertions.assertEquals(memberDto.getId() , saveId);
        //org.assertj.core.api =-- static import
        assertThat(result.getId()).isEqualTo(saveId);
    }
    @Test
    @DisplayName("아이드로 검색하기")
    void findById(){
        //Given
        MemberDto memberDto = new MemberDto();
        memberDto.setName("안유진");
        memberDto.setAddress("제주도");
        repository.save(memberDto);
        //When
        Long id = 1L;
        MemberDto findMember = repository.findById(id).get();

        //Then
        assertThat(findMember.getName()).isEqualTo("안유진");

    }
    @Test
    @DisplayName("아이드로 검색 실패")
    void findById_Fail(){
        //Given
        MemberDto memberDto = new MemberDto();
        memberDto.setName("안유진");
        memberDto.setAddress("제주도");
        repository.save(memberDto);
        //When
        Long id = 2L;
        MemberDto findMember = repository.findById(id).orElse(null);

        //Then
        assertThat(findMember).isEqualTo(null);

    }
}