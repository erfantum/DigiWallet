package com.example.digiwallet.service;

import com.example.digiwallet.exception.DigiException;
import com.example.digiwallet.model.wallet.entity.Wallet;
import org.springframework.data.domain.Example;

import java.util.List;

public interface WalletService {
    Wallet saveWallet(Wallet wallet) throws DigiException;

    Wallet updateWallet(Wallet wallet) throws DigiException;

    List<Wallet> findAllWallet();

    Wallet findWalletById(Long id) throws DigiException;

    List<Wallet> findWalletByExample(Example<Wallet> walletExample);

    void deleteWalletById(Long id) throws DigiException;

    Wallet findByWalletNumber(Long id) throws DigiException;

    void walletAuthentication(Wallet wallet) throws DigiException;

}
