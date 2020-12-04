package com.ridao.pagacoins.repository;

import com.ridao.pagacoins.model.Transaction;
import com.ridao.pagacoins.model.Wallet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findBySenderOrReceiver(Wallet wallet);
}
