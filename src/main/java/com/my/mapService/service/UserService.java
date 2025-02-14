package com.my.mapService.service;

import com.my.mapService.dto.MemberDto;
import com.my.mapService.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    //신규 회원가입
     String SignIn(UserDto userDto);

    //아이디로 검색하서 한개 찾기
    Optional<UserDto> findById(String userId);

    //전체 리스트 출력
    List<UserDto> findAll();

    //삭제처리
    void deleteById(String userId);

    //업데이트
    void update(UserDto userDto);
}
