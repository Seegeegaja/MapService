package com.my.mapService.service;

import com.my.mapService.dto.UserDto;
import com.my.mapService.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ListUserService implements UserService{

    private final UserRepository userRepository;

    public ListUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String SignIn(UserDto userDto) {
        Optional<UserDto> result = userRepository.findById(userDto.getUserId());
        if (result.isPresent()) {
            return result.get().getUserId();
        } else {
            UserDto save = userRepository.save(userDto);
            return save.getUserId();
        }
    }

    @Override
    public Optional<UserDto> findById(String userId) {
        Optional<UserDto> byId = userRepository.findById(userId);
        return byId;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(String userId) {
        userRepository.deleteById(userId);

    }

    @Override
    public void update(UserDto userDto) {
        userRepository.updateById(userDto.getUserId(), userDto);

    }
}
