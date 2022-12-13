package com.example.digiwallet.service.impl;

import com.example.digiwallet.exception.DigiException;
import com.example.digiwallet.model.user.entity.User;
import com.example.digiwallet.model.wallet.dao.WalletDao;
import com.example.digiwallet.model.wallet.entity.Wallet;
import com.example.digiwallet.model.wallet.entity.WalletStatus;
import com.example.digiwallet.service.UserService;
import com.example.digiwallet.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {
    final WalletDao walletDao;

    final UserService userService;

    Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    public WalletServiceImpl(WalletDao walletDao, UserService userService) {
        this.walletDao = walletDao;
        this.userService = userService;
    }


    @Override
    @Transactional
    public Wallet saveWallet(Wallet wallet) throws DigiException {
        wallet.setBalance(BigDecimal.valueOf(0));
        SecureRandom secureRandom=new SecureRandom();
        wallet.setWalletNumber(secureRandom.nextLong(0,99999999L));
        wallet.setWalletStatus(WalletStatus.ENABLED);
        String clientUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        wallet.setUser(userService.findUserByUsername(clientUsername));
        return walletDao.save(wallet);
    }

    @Override
    @Transactional
    public Wallet updateWallet(Wallet wallet) throws DigiException {
        try {
            return walletDao.save(wallet);
        }catch (Exception exception){
            logger.error("error during updating wallet");
            throw new DigiException(wallet.getName(),"not updated");
        }
    }

    @Override
    @Transactional
    public List<Wallet> findAllWallet() {
        return walletDao.findAll();
    }

    @Override
    @Transactional
    public Wallet findWalletById(Long id) throws DigiException {
        return walletDao.findById(id).orElseThrow(()->{
            logger.error("this walletId: "+id+"was not found");
            return new DigiException(id.toString(),"not found");
        });
    }

    @Override
    @Transactional
    public List<Wallet> findWalletByExample(Example<Wallet> walletExample) {
        return walletDao.findAll(walletExample);
    }

    @Override
    @Transactional
    public void deleteWalletById(Long id) throws DigiException {
        try {
            walletAuthentication(findWalletById(id));
            walletDao.deleteById(id);
        }catch (Exception exception){
            logger.error("error during delete wallet",exception);
            throw new DigiException(id.toString(),"was not found");
        }
    }

    @Override
    @Transactional
    public Wallet findByWalletNumber(Long id) throws DigiException {
        try {
            return walletDao.findByWalletNumber(id);
        }catch (Exception e){
            logger.error("error during find wallet by wallet number",e);
            throw new DigiException(id.toString(),"this wallet was not found");
        }
    }

    @Override
    @Transactional
    public void walletAuthentication(Wallet wallet) throws DigiException {
        String clientUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!wallet.getUser().getUsername().equals(clientUsername)){
            logger.error("this wallet: "+wallet.getId()+"is not for this username: "+clientUsername);
            throw new DigiException("client","This wallet is not yours");
        }
    }


}
