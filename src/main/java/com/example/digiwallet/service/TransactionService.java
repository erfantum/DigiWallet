package com.example.digiwallet.service;


import com.example.digiwallet.exception.DigiException;
import com.example.digiwallet.model.transaction.entity.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> findAllTransaction();

    Transaction findById(Long id) throws DigiException;

    Transaction withDrawOperation(Transaction transaction) throws DigiException;

    Transaction depositOperation(Transaction transaction)throws DigiException;

    Transaction transferOperation(Transaction transaction) throws DigiException;

    Transaction saveTransaction(Transaction transaction) throws DigiException;
}
