package com.example.digiwallet.service.impl;

import com.example.digiwallet.exception.DigiException;
import com.example.digiwallet.model.user.dao.UserDao;
import com.example.digiwallet.model.user.entity.User;
import com.example.digiwallet.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    final UserDao userDao;

    Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);


    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }

    @Transactional
    @Override
    public User updateUser(User user) {
        return userDao.save(user);
    }

    @Transactional
    @Override
    public List<User> findAllUser() {
        return userDao.findAll();
    }

    @Transactional
    @Override
    public User findUserById(Long id) {
        return userDao.findById(id).orElseThrow(RuntimeException::new);
    }

    @Transactional
    @Override
    public List<User> findUserByExample(Example<User> userExample) {
        return userDao.findAll(userExample);
    }

    @Transactional
    @Override
    public void deleteUserById(Long id) {
        userDao.deleteById(id);
    }

    @Transactional
    @Override
    public User findUserByUsername(String username) throws DigiException {
        try {
            return userDao.findByUsername(username);
        }catch (Exception e){
            logger.error("this user by "+username+" username not found");
            throw new DigiException(username,"not found");
        }

    }

    @Transactional
    @Override
    public User findUser() throws DigiException {
        String clientUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        return findUserByUsername(clientUsername);
    }
}
