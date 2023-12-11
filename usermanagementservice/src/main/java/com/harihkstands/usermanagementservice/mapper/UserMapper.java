package com.harihkstands.usermanagementservice.mapper;

import com.harihkstands.usermanagementservice.dto.UserDto;
import com.harihkstands.usermanagementservice.entity.User;

public class UserMapper {

    public static UserDto mapTOUserDto(User user){
        UserDto userDto= new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        return userDto;
    }
    public static User mapTOUser(UserDto userDto){
        User user= new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
        return user;
    }

}
