package com.my.mapService.service;

import com.my.mapService.dto.UserDto;
import com.my.mapService.repository.ListUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.InOrderImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ListUserServiceTest {
    ListUserRepository repository = new ListUserRepository();
    ListUserService service = new ListUserService(repository);

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    @DisplayName("회원가입")
    void addUser() {
        UserDto userDto = new UserDto();
        userDto.setUserId("싸고");
        userDto.setNickName("워붕넬라");
        userDto.setPassword("꼬물탱");
        String asd = service.SignIn(userDto);

        UserDto find = service.findById(asd).orElse(null);
        Assertions.assertThat(find.getUserId()).isEqualTo(userDto.getUserId());
    }

    @Test
    @DisplayName("중복검사")
    void 중복() {
        UserDto see = new UserDto();
        see.setUserId("씨게");
        see.setNickName("딜러보다딜넢을듯");
        see.setPassword("바쁘면어제오지그랬슈");
        service.SignIn(see);

        UserDto jjang = new UserDto();
        jjang.setUserId("칠갑산맑은물");
        jjang.setNickName("더탱글마이구미");
        jjang.setPassword("박고가자");
        service.SignIn(jjang);


        UserDto car = new UserDto();
        car.setUserId("씨게");
        car.setNickName("봉미사에");
        car.setPassword("란개발딱게");
        String chill = service.SignIn(car);

        Assertions.assertThat(chill).isEqualTo(see.getUserId());

    }

    @Test
    @DisplayName("찾기")
    void 찾기() {
        UserDto see = new UserDto();
        see.setUserId("씨게");
        see.setNickName("딜러보다딜넢을듯");
        see.setPassword("바쁘면어제오지그랬슈");
        service.SignIn(see);

        UserDto jjang = new UserDto();
        jjang.setUserId("칠갑산맑은물");
        jjang.setNickName("더탱글마이구미");
        jjang.setPassword("박고가자");
        String find = service.SignIn(jjang);

        Optional<UserDto> one = service.findById("칠갑산맑은물");

        Assertions.assertThat(one.get().getUserId()).isEqualTo(jjang.getUserId());
        Assertions.assertThat(one.get().getNickName()).isEqualTo(jjang.getNickName());

    }

    @Test
    @DisplayName("전체리스트")
    void findAll() {
        UserDto see = new UserDto();
        see.setUserId("씨게");
        see.setNickName("딜러보다딜넢을듯");
        see.setPassword("바쁘면어제오지그랬슈");
        service.SignIn(see);

        UserDto jjang = new UserDto();
        jjang.setUserId("칠갑산맑은물");
        jjang.setNickName("더탱글마이구미");
        jjang.setPassword("박고가자");
        service.SignIn(jjang);

        List<UserDto> list = service.findAll();
        Assertions.assertThat(list.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("아뒤 받아서 삭제")
    void deleteId() {
        UserDto see = new UserDto();
        see.setUserId("씨게");
        see.setNickName("딜러보다딜넢을듯");
        see.setPassword("바쁘면어제오지그랬슈");
        service.SignIn(see);

        UserDto jjang = new UserDto();
        jjang.setUserId("칠갑산맑은물");
        jjang.setNickName("더탱글마이구미");
        jjang.setPassword("박고가자");
        service.SignIn(jjang);

        service.deleteById("칠갑산맑은물");

        List<UserDto> list = service.findAll();
        Assertions.assertThat(list.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("업데이트")
    void update() {
        UserDto see = new UserDto();
        see.setUserId("씨게");
        see.setNickName("딜러보다딜넢을듯");
        see.setPassword("바쁘면어제오지그랬슈");
        service.SignIn(see);

        UserDto jjang = new UserDto();
        jjang.setUserId("씨게");
        jjang.setNickName("더탱글마이구미");
        jjang.setPassword("박고가자");

        service.update(jjang);
        UserDto result = service.findById("씨게").get();

        Assertions.assertThat(result.getNickName()).isEqualTo(jjang.getNickName());
    }
}