package com.ridao.pagacoins.repository;

import com.ridao.pagacoins.model.User;
import com.ridao.pagacoins.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    List<Wallet> findByUser(User user);
}