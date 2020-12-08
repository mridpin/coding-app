package com.ridao.pagacoins.service;

import com.ridao.pagacoins.model.Wallet;
import com.ridao.pagacoins.repository.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class WalletServiceTest {

    WalletService walletService;

    @Mock
    WalletRepository walletRepository;

    @BeforeEach
    public void setup () {
        MockitoAnnotations.initMocks(this);
        walletService = new WalletService();
        walletService.repository = walletRepository;
    }

    @Test
    public void testCheckBalanceExists() {
        Double amount = 10.0;
        Wallet wallet = new Wallet();
        wallet.setBalance(11.0);
        assertTrue(walletService.checkBalance(wallet, amount));
    }

    @Test
    public void testCheckBalanceDoesNotExist() {
        Double amount = 10.0;
        Wallet wallet = new Wallet();
        wallet.setBalance(9.0);
        assertFalse(walletService.checkBalance(wallet, amount));
    }

    @Test
    public void testCheckBalanceIsSame() {
        Double amount = 11.0;
        Wallet wallet = new Wallet();
        wallet.setBalance(11.0);
        assertTrue(walletService.checkBalance(wallet, amount));
    }

    @Test
    public void testMakeTransactionSuccess() {
        Wallet sender = new Wallet();
        sender.setId(1L);
        sender.setBalance(10.0);
        Wallet beneficiary = new Wallet();
        beneficiary.setId(2L);
        beneficiary.setBalance(20.0);

        when(walletRepository.save(any())).thenReturn(null);
        walletService.makeTransaction(sender, beneficiary, 10.0);
        assertEquals(sender.getBalance(), 0.0);
        assertEquals(beneficiary.getBalance(), 30.0);
    }

    @Test
    public void testMakeTransactionZero() {
        Wallet sender = new Wallet();
        sender.setId(1L);
        sender.setBalance(0.0);
        Wallet beneficiary = new Wallet();
        beneficiary.setId(2L);
        beneficiary.setBalance(0.0);

        when(walletRepository.save(any())).thenReturn(null);
        walletService.makeTransaction(sender, beneficiary, 0.0);
        assertEquals(sender.getBalance(), 0.0);
        assertEquals(beneficiary.getBalance(), 0.0);
    }

    @Test
    public void testMakeTransactionNegativeBalance() {
        Wallet sender = new Wallet();
        sender.setId(1L);
        sender.setBalance(10.0);
        Wallet beneficiary = new Wallet();
        beneficiary.setId(2L);
        beneficiary.setBalance(20.0);

        when(walletRepository.save(any())).thenReturn(null);
        walletService.makeTransaction(sender, beneficiary, 20.0);
        assertEquals(sender.getBalance(), -10.0);
        assertEquals(beneficiary.getBalance(), 40.0);
    }

    @Test
    public void testMakeTransactionNegativeAmount() {
        Wallet sender = new Wallet();
        sender.setId(1L);
        sender.setBalance(10.0);
        Wallet beneficiary = new Wallet();
        beneficiary.setId(2L);
        beneficiary.setBalance(20.0);

        when(walletRepository.save(any())).thenReturn(null);
        walletService.makeTransaction(sender, beneficiary, -20.0);
        assertEquals(sender.getBalance(), 30.0);
        assertEquals(beneficiary.getBalance(), 0.0);
    }

}