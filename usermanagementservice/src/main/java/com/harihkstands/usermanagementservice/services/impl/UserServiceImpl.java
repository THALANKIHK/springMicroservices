package com.harihkstands.usermanagementservice.services.impl;

import com.harihkstands.usermanagementservice.dto.UserDto;
import com.harihkstands.usermanagementservice.entity.User;
import com.harihkstands.usermanagementservice.exception.EmailAlreadyExistsException;
import com.harihkstands.usermanagementservice.exception.ResourceNotFoundException;
import com.harihkstands.usermanagementservice.mapper.UserMapper;
import com.harihkstands.usermanagementservice.repository.UserRepository;
import com.harihkstands.usermanagementservice.services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        //User user= UserMapper.mapTOUser(userDto);
        Optional<User> optionalUser= userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email already exists for the User");
        }
        User user= modelMapper.map(userDto,User.class);
        User savedUser=userRepository.save(user);
       // UserDto saveduserDto=UserMapper.mapTOUserDto(savedUser);
        UserDto saveduserDto=modelMapper.map(savedUser,UserDto.class);
        return saveduserDto;
    }

    @Override
    public List<UserDto> getUsersList() {
        List<User> userList = userRepository.findAll();
        //userList.stream().map(UserMapper::mapTOUserDto).collect(Collectors.toList());
        return userList.stream().map((user)->modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(long userId) {
        User optionalUser=userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("optionalUser","id",userId)
        );
        return modelMapper.map(optionalUser, UserDto.class);
    }

    public UserDto updateUser(UserDto userDto){
       User existingUser= userRepository.findById(userDto.getId()).orElseThrow(
               ()-> new ResourceNotFoundException("User","id",userDto.getId()));
       existingUser.setEmail(userDto.getEmail());
       existingUser.setFirstName(userDto.getFirstName());
       existingUser.setLastName(userDto.getLastName());
       User updatedUser = userRepository.save(existingUser);
       return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUser(long userId) {
        User optionalUser=userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("optionalUser","id",userId)
        );

        userRepository.deleteById(userId);
    }
}
