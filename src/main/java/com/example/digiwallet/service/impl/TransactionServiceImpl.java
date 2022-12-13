package com.example.digiwallet.service.impl;

import com.example.digiwallet.exception.DigiException;
import com.example.digiwallet.model.transaction.dao.TransactionDao;
import com.example.digiwallet.model.transaction.entity.Transaction;
import com.example.digiwallet.model.transaction.entity.TransactionStatus;
import com.example.digiwallet.model.transaction.entity.TransactionType;
import com.example.digiwallet.model.wallet.entity.Wallet;
import com.example.digiwallet.model.wallet.entity.WalletStatus;
import com.example.digiwallet.service.TransactionService;
import com.example.digiwallet.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    final TransactionDao transactionDao;

    Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
    final WalletService walletService;

    public TransactionServiceImpl(TransactionDao transactionDao, WalletService walletService) {
        this.transactionDao = transactionDao;
        this.walletService = walletService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Transaction saveTransaction(Transaction transaction) throws DigiException {
        try {
            return transactionDao.save(transaction);
        }catch (Exception e){
            logger.error("error during saving transaction",e);
            throw new DigiException(e.getMessage(),"not saved");
        }
    }

    @Override
    @Transactional
    public List<Transaction> findAllTransaction() {
        return transactionDao.findAll();
    }

    @Transactional
    @Override
    public Transaction findById(Long id) throws DigiException {
        return transactionDao.findById(id).orElseThrow(()-> {
            logger.error("error occurred in method: transaction.findById");
            return new DigiException("client", "Wallet was not found");
        });
    }

    @Override
    @Transactional
    public Transaction withDrawOperation(Transaction transaction) throws DigiException {
        Wallet wallet = validateCard(transaction.getSource());
        if (wallet.getBalance().compareTo(transaction.getAmount()) < 0) {
            logger.error("the Balance of Wallet: "+wallet.getWalletNumber()+" is less than amount of Transaction of "+wallet.getUser().getId()+wallet.getUser().getUsername());
            throw new DigiException("client","Your balance is less than amount");
        }
        wallet.setBalance(wallet.getBalance().subtract(transaction.getAmount()));
        transaction.setTransactionType(TransactionType.WITHDRAWAL);
        return getTransaction(transaction, wallet);
    }

    @Override
    @Transactional
    public Transaction depositOperation(Transaction transaction) throws DigiException {
        Wallet wallet = validateCard(transaction.getSource());
        wallet.setBalance(wallet.getBalance().add(transaction.getAmount()));
        transaction.setTransactionType(TransactionType.DEPOSIT);
        return getTransaction(transaction, wallet);
    }

    @Override
    @Transactional
    public Transaction transferOperation(Transaction transaction) throws DigiException {
        Wallet sourceWallet = validateCard(transaction.getSource());
        Wallet destinationWallet = validateCard(transaction.getDestination());
        if (sourceWallet.getBalance().compareTo(transaction.getAmount()) < 0) {
            logger.error("the Balance of Wallet: "+sourceWallet.getWalletNumber()+" is less than amount of Transaction of "+sourceWallet.getUser().getId()+sourceWallet.getUser().getUsername());
            throw new DigiException("client","Your balance is less than amount");
        }
        sourceWallet.setBalance(sourceWallet.getBalance().subtract(transaction.getAmount()));
        destinationWallet.setBalance(destinationWallet.getBalance().add(transaction.getAmount()));

        try {
            walletService.updateWallet(sourceWallet);
            walletService.updateWallet(destinationWallet);
            transaction.setTransactionType(TransactionType.TRANSFER);
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
            transaction.setWallet(sourceWallet);
            return saveTransaction(transaction);
        }catch (Exception e){
            walletService.updateWallet(sourceWallet);
            walletService.updateWallet(destinationWallet);
            transaction.setTransactionType(TransactionType.TRANSFER);
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transaction.setWallet(sourceWallet);
            saveTransaction(transaction);
            logger.error("error occurred during saving transaction in transaction.transferOperation");
            throw new DigiException("client","An Error occurred during transfer");
        }
    }

    private Wallet validateCard(Long walletNumber) throws DigiException {
        Wallet wallet = walletService.findByWalletNumber(walletNumber);
        if (wallet == null) {
            logger.error("error during validation ,Wallet was not found");
            throw new DigiException("client","Wallet was not found");
        }
        if (wallet.getWalletStatus() == WalletStatus.DISABLED) {
            logger.error("error during validation ,Wallet : "+walletNumber+" is disabled");
            throw new DigiException("client","Your Wallet is disabled");
        }
        walletService.walletAuthentication(wallet);
        return wallet;
    }

    private Transaction getTransaction(Transaction transaction, Wallet wallet) throws DigiException {
        try {
            Wallet updatedWallet = walletService.updateWallet(wallet);
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
            transaction.setWallet(updatedWallet);
            return saveTransaction(transaction);
        }catch (Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            saveTransaction(transaction);
            logger.error("error occurred during saving transaction in getTransaction");
            throw new DigiException("client","transaction not saved");
        }
    }
}
