package com.my.mapService.repository;

import com.my.mapService.dto.MemberDto;
import com.my.mapService.dto.UserDto;

import java.util.List;
import java.util.Optional;


public interface UserRepository {
    //1.회원을 멥에 저장하는 기능(userStore : List)
    UserDto save(UserDto userDto);

    //2.특정 아이디를 가지고 검색 하는 기능
    Optional<UserDto> findById(String userId);

    //3. 전체 데이터를 검색 하는 기능
    List<UserDto> findAll();

    //5.삭제를 가지고 삭제하기
    void deleteById(String userId);

    //6.id를 가지고 수정하기
    UserDto updateById(String userId, UserDto userDto);
}
