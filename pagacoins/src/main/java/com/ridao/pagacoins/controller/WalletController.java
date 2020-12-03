package com.ridao.pagacoins.controller;

import com.ridao.pagacoins.dto.WalletDTO;
import com.ridao.pagacoins.model.User;
import com.ridao.pagacoins.model.Wallet;
import com.ridao.pagacoins.service.UserService;
import com.ridao.pagacoins.service.WalletService;
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
    WalletService walletService;

    @Autowired
    UserService userService;

    @Autowired
    private ModelMapper mapper;


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(path = "/user/{id}/wallet", produces = "application/json; " +
            "charset=UTF-8")
    @ResponseBody
    public ResponseEntity<List<WalletDTO>> getAllWalletsFromUser(@PathVariable(
            "id") Long id) {
        // Get User first, return 404 if no user
        Optional<User> user = userService.getUserById(id);
        if (!user.isPresent()) {return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        List<WalletDTO> wallets =
                walletService.getWalletsFromUser(user.get())
                        .stream()
                        .map(this::entityToDTO)
                        .collect(Collectors.toList());
        logger.info("Wallets found -> {}", wallets.size());
        return new ResponseEntity<List<WalletDTO>>(wallets, HttpStatus.OK);
    }

    @GetMapping(path = "/wallet/{id}", produces =
            "application" +
            "/json; " +
            "charset=UTF-8")
    @ResponseBody
    public ResponseEntity<WalletDTO> getWalletById (@PathVariable(
            "id") Long id) {
        Optional<Wallet> wallet = walletService.getWalletById(id);
        logger.info("Wallet found -> {}", wallet.hashCode());
        if (wallet.isPresent()) {
            return new ResponseEntity<WalletDTO>(entityToDTO(wallet.get()),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private WalletDTO entityToDTO (Wallet wallet) {
        WalletDTO walletDto = mapper.map(wallet, WalletDTO.class);
        return walletDto;
    }

}
