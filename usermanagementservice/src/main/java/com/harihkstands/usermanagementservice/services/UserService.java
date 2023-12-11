package com.harihkstands.usermanagementservice.services;

import com.harihkstands.usermanagementservice.dto.UserDto;
import com.harihkstands.usermanagementservice.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userdto);
    List<UserDto> getUsersList();
    UserDto getUserById(long userId);

    UserDto updateUser(UserDto userDto);
    void deleteUser(long userId);
}
