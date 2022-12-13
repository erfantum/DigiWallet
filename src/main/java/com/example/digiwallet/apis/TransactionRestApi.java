package com.example.digiwallet.apis;

import com.example.digiwallet.exception.DigiException;
import com.example.digiwallet.mapper.TransactionMapper;
import com.example.digiwallet.model.transaction.dto.TransactionDto;
import com.example.digiwallet.model.transaction.entity.Transaction;
import com.example.digiwallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transaction")
public class TransactionRestApi {
    @Autowired
    TransactionService transactionService;

    @Autowired
    TransactionMapper transactionMapper;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/withdraw")
    public Transaction withdrawFromWallet(@RequestBody @Valid TransactionDto transaction) throws DigiException {
        return transactionService.withDrawOperation(transactionMapper.dtoToEntity(transaction));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/deposit")
    public Transaction depositToWallet(@RequestBody @Valid TransactionDto transaction) throws DigiException {
        return transactionService.depositOperation(transactionMapper.dtoToEntity(transaction));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/wtow")
    public Transaction walletToWallet(@RequestBody @Valid TransactionDto transaction) throws DigiException {
        return transactionService.transferOperation(transactionMapper.dtoToEntity(transaction));
    }

}
