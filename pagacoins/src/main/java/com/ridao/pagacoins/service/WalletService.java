package com.ridao.pagacoins.service;

import com.ridao.pagacoins.model.User;
import com.ridao.pagacoins.model.Wallet;
import com.ridao.pagacoins.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    WalletRepository repository;

    public List<Wallet> getWalletsFromUser(User user) {
        return repository.findByUser(user);
    }

    public Optional<Wallet> getWalletById(Long id) {
        return repository.findById(id);
    }
}
