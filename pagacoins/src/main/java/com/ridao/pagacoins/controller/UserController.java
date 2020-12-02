package com.ridao.pagacoins.controller;

import com.ridao.pagacoins.model.User;
import com.ridao.pagacoins.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository repository;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/users")
    public List<User> listUsers() {
        List<User> users = repository.findAll();
        logger.info("Users found -> {}", users.size());
        return users;
    }
}
