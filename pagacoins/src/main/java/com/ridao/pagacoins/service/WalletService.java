package com.ridao.pagacoins.service;

import com.ridao.pagacoins.model.User;
import com.ridao.pagacoins.model.Wallet;
import com.ridao.pagacoins.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WalletService {

    @Autowired
    WalletRepository repository;

    public List<Wallet> getWalletsFromUser(User user) {
        return repository.findByUser(user);
    }

}
