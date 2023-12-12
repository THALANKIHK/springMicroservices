package com.harihkstands.usermanagementservice.controllers;

import com.harihkstands.usermanagementservice.dto.UserDto;
import com.harihkstands.usermanagementservice.entity.User;
import com.harihkstands.usermanagementservice.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("hello")
    public String helloWorld(){
        return "hi Hk!";
    }
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsersList(){
        List<UserDto> userList=userService.getUsersList();
        return new ResponseEntity<>(userList,HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long id){
      UserDto user=  userService.getUserById(id);
      return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updatedUser(@PathVariable long id,@Valid @RequestBody UserDto userDto){
        userDto.setId(id);
        UserDto savedUser = userService.updateUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

    }
    @DeleteMapping ("{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("deleted succesfully",HttpStatus.OK);
    }
}
