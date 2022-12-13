package com.example.digiwallet.apis;

import com.example.digiwallet.exception.DigiException;
import com.example.digiwallet.mapper.WalletMapper;
import com.example.digiwallet.model.wallet.dto.WalletDto;
import com.example.digiwallet.model.wallet.entity.Wallet;
import com.example.digiwallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/wallet")
public class WalletRestApis {
    @Autowired
    WalletService walletService;
    @Autowired
    WalletMapper walletMapper;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public WalletDto saveWallet(@RequestBody @Valid WalletDto wallet) throws DigiException {
        return walletMapper.entityToDto(walletService.saveWallet(walletMapper.dtoToEntity(wallet)));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping
    public WalletDto updateWallet(@RequestBody @Valid WalletDto wallet) throws DigiException {
        return walletMapper.entityToDto(walletService.updateWallet(walletMapper.dtoToEntity(wallet)));
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping
    public void deleteWallet(Long id) throws DigiException {
        walletService.deleteWalletById(id);
    }
}
