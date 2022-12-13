package com.example.digiwallet.apis;

import com.example.digiwallet.exception.DigiException;
import com.example.digiwallet.mapper.UserMapper;
import com.example.digiwallet.model.user.dto.UserDto;
import com.example.digiwallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserRestApis {
    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @PreAuthorize("hasRole('USER')")
    @PutMapping
    public UserDto updateUser(@RequestBody @Valid UserDto user){
        return userMapper.entityToDto(userService.updateUser(userMapper.dtoToEntity(user)));
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping
    public UserDto findUser() throws DigiException {
        return userMapper.entityToDto(userService.findUser());
    }
}
