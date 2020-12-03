package com.ridao.pagacoins.controller;

import com.ridao.pagacoins.dto.UserDTO;
import com.ridao.pagacoins.model.User;
import com.ridao.pagacoins.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    private ModelMapper mapper;


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(path = "", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<List<UserDTO>> listUsers() {
        List<UserDTO> users =
                service.getUsers()
                        .stream()
                        .map(this::entityToDTO)
                        .collect(Collectors.toList());
        logger.info("Users found -> {}", users.size());
        return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
    }

    private UserDTO entityToDTO (User user) {
        UserDTO userDto = mapper.map(user, UserDTO.class);
        return userDto;
    }

}
