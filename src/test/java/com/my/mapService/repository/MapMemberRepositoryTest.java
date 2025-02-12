package com.my.mapService.repository;

import com.my.mapService.dto.MemberDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        repository.save(memberDto);

//        When
        MemberDto result = repository.findById(memberDto.getId()).get();
//        Then
//        Long saveId = 1L;
        //org.junit.juipter.api
        Assertions.assertEquals(memberDto , result);
        //org.assertj.core.api =-- static import
        assertThat(result).isEqualTo(result);
    }
    @Test
    @DisplayName("아이디로 검색하기")
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
    @DisplayName("아이디로 검색 실패")
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
    @Test
    @DisplayName("전체검색 테스트")
    void findAll() {
//        Given
        MemberDto jang = new MemberDto();
        jang.setName("장원영");
        jang.setAddress("서울");
        repository.save(jang);
        MemberDto kar = new MemberDto();
        kar.setName("카리나");
        kar.setAddress("제주");
        repository.save(kar);
//        When
        List<MemberDto> list = repository.findAll();
//        Then
        assertThat(list.size()).isEqualTo(2);
    }
    @Test
    @DisplayName("이름으로 찾기")
    void findByName(){
        //장원영하고 안유진 입력 장원영으로 검색
        //찾은 Member 객체가  장원영객체와 비교 같은지 확인
        //Given
        MemberDto kar = new MemberDto();
        kar.setName("카리나");
        kar.setAddress("서울");
        repository.save(kar);

        MemberDto win = new MemberDto();
        win.setName("윈터");
        win.setAddress("남한");
        repository.save(win);
        //When
//        MemberDto findMember = repository.findById(id).orElse(null);

        MemberDto chill = repository.findByName("윈터").get();
        //Then
//        assertThat(findMember.getName()).isEqualTo("안유진");
        assertThat(chill).isEqualTo(win);
    }
    @Test
    @DisplayName("아이디로 삭제 하기")
    void deleteById(){
        //Given
        MemberDto kar = new MemberDto();
        kar.setName("카리나");
        kar.setAddress("서울");
        repository.save(kar);
        //When
        repository.deleteById(1L);
//        MemberDto chill = repository.findById(3L).orElse(null);
        //Then
//        assertThat(chill).isEqualTo(kar);
    }
    @Test
    @DisplayName("데이터 수정하기 확인 테스트")
    void updateById() {
        //Given
        //씨게 입력(씨게,서울) -->id=1
        MemberDto see = new MemberDto();
        see.setName("씨게");
        see.setAddress("전주");
        repository.save(see);

        //When
        //객체 생성 (1,씨게,제주)
        MemberDto upSee = new MemberDto();
        upSee.setId(1L);
        upSee.setName("씨게");
        upSee.setAddress("제주");
        repository.updateById(1L, upSee);
        //updateById(1,updateMember)
        //Then
        //id= 1번을 읽어 와서 주소가 바뀌었는지 확인
        MemberDto cill = repository.findById(1L).orElse(null);
        assertThat(cill.getAddress()).isEqualTo("제주");
    }

}