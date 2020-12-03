package com.ridao.pagacoins.controller;

import com.ridao.pagacoins.dto.UserDTO;
import com.ridao.pagacoins.dto.WalletDTO;
import com.ridao.pagacoins.model.User;
import com.ridao.pagacoins.model.Wallet;
import com.ridao.pagacoins.repository.UserRepository;
import com.ridao.pagacoins.repository.WalletRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class WalletController {

    @Autowired
    WalletRepository repository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(path = "/user/{id}/wallets", produces = "application/json; " +
            "charset=UTF-8")
    @ResponseBody
    public ResponseEntity<List<WalletDTO>> listWalletsFromUser(@PathVariable(
            "id") Long id) {
        // Get User first, return 404 if no user
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        List<WalletDTO> wallets =
                repository.findByUser(user.get())
                        .stream()
                        .map(this::entityToDTO)
                        .collect(Collectors.toList());
        logger.info("Wallets found -> {}", wallets.size());
        return new ResponseEntity<List<WalletDTO>>(wallets, HttpStatus.OK);
    }

    private WalletDTO entityToDTO (Wallet wallet) {
        WalletDTO walletDto = mapper.map(wallet, WalletDTO.class);
        return walletDto;
    }

}
