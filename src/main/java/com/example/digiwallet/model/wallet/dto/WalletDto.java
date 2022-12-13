package com.example.digiwallet.model.wallet.dto;

import com.example.digiwallet.model.ValidationGroups;
import com.example.digiwallet.model.transaction.dto.TransactionDto;
import com.example.digiwallet.model.wallet.entity.WalletStatus;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class WalletDto {
    private Long id;

    @Positive
    private Long walletNumber;

    @NotBlank(message = "must be not empty")
    private String name;

    @PositiveOrZero
    @Digits(fraction = 2,integer = 16)
    private BigDecimal balance;

    private WalletStatus walletStatus;

    private Date createDate;

    private Date updateDate;

    private List<TransactionDto> transactionDtoList;

    public WalletDto() {
    }

    public WalletDto(Long id, Long walletNumber, String name, BigDecimal balance, WalletStatus walletStatus, Date createDate, Date updateDate, List<TransactionDto> transactionDtoList) {
        this.id = id;
        this.walletNumber = walletNumber;
        this.name = name;
        this.balance = balance;
        this.walletStatus = walletStatus;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.transactionDtoList = transactionDtoList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWalletNumber() {
        return walletNumber;
    }

    public void setWalletNumber(Long walletNumber) {
        this.walletNumber = walletNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public WalletStatus getWalletStatus() {
        return walletStatus;
    }

    public void setWalletStatus(WalletStatus walletStatus) {
        this.walletStatus = walletStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public List<TransactionDto> getTransactionDtoList() {
        return transactionDtoList;
    }

    public void setTransactionDtoList(List<TransactionDto> transactionDtoList) {
        this.transactionDtoList = transactionDtoList;
    }
}
