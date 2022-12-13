package com.example.digiwallet.model.wallet.dao;

import com.example.digiwallet.model.user.entity.User;
import com.example.digiwallet.model.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletDao extends JpaRepository<Wallet,Long> {
    Wallet findByWalletNumber(Long walletNumber);

    List<Wallet> findByUser(User user);
}
