package com.example.digiwallet.model.transaction.dao;

import com.example.digiwallet.model.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDao extends JpaRepository<Transaction,Long> {
}
