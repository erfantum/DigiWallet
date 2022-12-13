package com.example.digiwallet.service.impl;

import com.example.digiwallet.DigiWalletApplication;
import com.example.digiwallet.exception.DigiException;
import com.example.digiwallet.model.transaction.dao.TransactionDao;
import com.example.digiwallet.model.transaction.entity.Transaction;
import com.example.digiwallet.model.transaction.entity.TransactionType;
import com.example.digiwallet.model.user.entity.User;
import com.example.digiwallet.model.wallet.entity.Wallet;
import com.example.digiwallet.model.wallet.entity.WalletStatus;
import com.example.digiwallet.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DigiWalletApplication.class)
@EnableTransactionManagement
class TransactionServiceImplTest {
    @Autowired
    TransactionService transactionService;

    @Autowired
    TransactionDao transactionDao;

    @BeforeEach
    void setUp() {
        Wallet wallet = new Wallet();
        wallet.setId(1L);
        wallet.setName("one");
        wallet.setWalletStatus(WalletStatus.ENABLED);
        wallet.setWalletNumber(1222L);
        wallet.setBalance(BigDecimal.valueOf(123000L));
        User userEntity = new User();
        userEntity.setFirstName("erf");
        userEntity.setLastName("gh");
        userEntity.setUsername("erfantum");
        wallet.setUser(userEntity);
        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(11L));
        transaction.setTransactionType(TransactionType.WITHDRAWAL);
        transaction.setDestination(1000L);
        transaction.setSource(2000L);
        transaction.setWallet(wallet);
    }
    @Test
    void save () {
        Transaction transaction=new Transaction();
        transaction.setAmount(BigDecimal.valueOf(120L));
        Transaction saveTransaction = null;
        try {
            saveTransaction = transactionService.saveTransaction(transaction);
        } catch (DigiException e) {
            throw new RuntimeException(e);
        }
        Long byId= transaction.getId();
        Assertions.assertEquals(saveTransaction.getId(), byId);

    }
}