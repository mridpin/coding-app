package com.ridao.pagacoins.service;

import com.ridao.pagacoins.model.Transaction;
import com.ridao.pagacoins.model.Wallet;
import com.ridao.pagacoins.repository.TransactionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public Transaction createTransaction (Wallet sender, Wallet beneficiary, Double amount) {
        Transaction transaction = new Transaction();
        transaction.setBeneficiary(beneficiary);
        transaction.setSender(sender);
        transaction.setAmount(amount);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsFromWallet(Wallet wallet) {
        return transactionRepository.findBySenderOrReceiver(wallet);
    }

}
