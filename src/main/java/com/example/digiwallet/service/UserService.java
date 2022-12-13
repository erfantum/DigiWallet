package com.example.digiwallet.service;

import com.example.digiwallet.exception.DigiException;
import com.example.digiwallet.model.user.entity.User;
import org.springframework.data.domain.Example;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    User updateUser(User user);

    List<User> findAllUser();

    User findUserById(Long id) throws DigiException;

    List<User> findUserByExample(Example<User> userExample);

    void deleteUserById(Long id);

    User findUserByUsername(String username) throws DigiException;

    User findUser() throws DigiException;
}
