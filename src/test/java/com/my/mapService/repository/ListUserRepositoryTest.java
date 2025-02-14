package com.my.mapService.repository;

import static org.assertj.core.api.Assertions.assertThat;
import com.my.mapService.dto.UserDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ListUserRepositoryTest {
    ListUserRepository repository = new ListUserRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    @DisplayName("저장")
    void save() {
        UserDto userDto = new UserDto();
        userDto.setUserId("씨게");
        userDto.setNickName("짱구");
        userDto.setPassword("칠갑산맑은물");
        repository.save(userDto);
        UserDto user = new UserDto();
        user.setUserId("쎄게");
        user.setNickName("바이트피자");
        user.setPassword("발딱게");
        repository.save(user);
        UserDto result = repository.findById(user.getUserId()).get();
        Assertions.assertThat(result.getUserId()).isEqualTo("쎄게");

    }

    @Test
    @DisplayName("아뒤로 검색")
    void findById() {
        UserDto userDto = new UserDto();
        userDto.setUserId("씨게");
        userDto.setNickName("짱구");
        userDto.setPassword("칠갑산맑은물");
        repository.save(userDto);
        UserDto user = new UserDto();
        user.setUserId("쎄게");
        user.setNickName("바이트피자");
        user.setPassword("발딱게");
        repository.save(user);

        UserDto find = repository.findById(userDto.getUserId()).get();
        System.out.println(find);
        Assertions.assertThat(find.getUserId()).isEqualTo("씨게");
    }

    @Test
    @DisplayName("아디검색실패")
    void findBuId_Fail() {
        UserDto userDto = new UserDto();
        userDto.setUserId("씨게");
        userDto.setNickName("짱구");
        userDto.setPassword("칠갑산맑은물");
        repository.save(userDto);

        UserDto find = repository.findById("바이트피자").orElse(null);

        Assertions.assertThat(find).isEqualTo(null);
    }

    @Test
    @DisplayName("전체리스트 검색")
    void findAll() {
        UserDto userDto = new UserDto();
        userDto.setUserId("씨게");
        userDto.setNickName("짱구");
        userDto.setPassword("칠갑산맑은물");
        repository.save(userDto);
        UserDto user = new UserDto();
        user.setUserId("쎄게");
        user.setNickName("바이트피자");
        user.setPassword("발딱게");
        repository.save(user);

        List<UserDto> list = repository.findAll();
        System.out.println(list);

        Assertions.assertThat(list.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("아뒤로 지우기")
    void deleteById() {
        UserDto userDto = new UserDto();
        userDto.setUserId("씨게");
        userDto.setNickName("짱구");
        userDto.setPassword("칠갑산맑은물");
        repository.save(userDto);
        UserDto user = new UserDto();
        user.setUserId("쎄게");
        user.setNickName("바이트피자");
        user.setPassword("발딱게");
        repository.save(user);

        repository.deleteById("쎄게");

        List<UserDto> list = repository.findAll();
        Assertions.assertThat(list.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("수정")
    void updateBtId() {
        UserDto userDto = new UserDto();
        userDto.setUserId("씨게");
        userDto.setNickName("짱구");
        userDto.setPassword("칠갑산맑은물");
        repository.save(userDto);
        UserDto user = new UserDto();
        user.setUserId("씨게");
        user.setNickName("바이트피자");
        user.setPassword("발딱게");
        repository.updateById("씨게", user);
        UserDto chill = repository.findById(userDto.getUserId()).get();

    }



}