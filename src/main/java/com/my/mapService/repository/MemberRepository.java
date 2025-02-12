package com.my.mapService.repository;

import com.my.mapService.dto.MemberDto;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    //1.회원을 멥에 저장하는 기능
    MemberDto save(MemberDto memberDto);

    //2.특정 아이디를 가지고 검색 하는 기능
    Optional<MemberDto> findById(Long id);

    //3. 전체 데이터를 검색 하는 기능
    List<MemberDto> findAll();

    //4. 이름으로 검색하기
    Optional<MemberDto> findByName(String name);

    //5.삭제(id ,key)를 가지고 삭제하기
    void deleteById(Long id);

    //6.id를 가지고 수정하기
    MemberDto updateById(Long memberId, MemberDto memberDto);
}
