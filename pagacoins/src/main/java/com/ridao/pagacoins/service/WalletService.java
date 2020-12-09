package com.ridao.pagacoins.service;

import com.ridao.pagacoins.model.User;
import com.ridao.pagacoins.model.Wallet;
import com.ridao.pagacoins.repository.WalletRepository;
import com.ridao.pagacoins.utils.HashIdUtil;
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

    public Optional<Wallet> getWalletByHash(String hash) {
        return repository.findByHash(hash);
    }

    public boolean checkBalance(Wallet wallet, Double amount) {
        return wallet.getBalance() >= amount;
    }

    public Wallet makeTransaction(Wallet sender, Wallet beneficiary, Double amount) {
        addBalance(beneficiary, amount);
        removeBalance(sender, amount);
        repository.save(sender);
        repository.save(beneficiary);
        return sender;
    }

    private void removeBalance(Wallet sender, Double amount) {
        Double currentBalance = sender.getBalance();
        sender.setBalance(currentBalance - amount);
    }

    private void addBalance(Wallet beneficiary, Double amount) {
        Double currentBalance = beneficiary.getBalance();
        beneficiary.setBalance(currentBalance + amount);
    }
}
