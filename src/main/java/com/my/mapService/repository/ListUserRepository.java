package com.my.mapService.repository;

import com.my.mapService.dto.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListUserRepository implements UserRepository{

    public static List<UserDto> store = new ArrayList<>();

    @Override
    public UserDto save(UserDto userDto) {
        store.add(userDto);
        return userDto;
    }

    @Override
    public Optional<UserDto> findById(String userId) {
        Optional<UserDto> result = store.stream()
                .filter(x -> x.getUserId().equals(userId)).findAny();

        return result;
    }

    @Override
    public List<UserDto> findAll() {
        List<UserDto> userDtos = new ArrayList<>(store);
        return userDtos;
    }

    @Override
    public void deleteById(String userId) {
        store.removeIf(userDto->userDto.getUserId().equals(userId));
    }

    @Override
    public UserDto updateById(String userId, UserDto userDto) {
        for (int i = 0; i < store.size(); i++) {
            UserDto search = store.get(i);
            if (search.getUserId().equals(userId)) {
                store.set(i, userDto);
                return userDto;
            }
        }
        return null;
    }

    public void clearStore() {
        store.clear();
    }
}
