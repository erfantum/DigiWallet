package com.example.digiwallet.model.wallet.entity;

import com.example.digiwallet.model.Audit;
import com.example.digiwallet.model.ValidationGroups;
import com.example.digiwallet.model.transaction.entity.Transaction;
import com.example.digiwallet.model.user.entity.User;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Entity
@Table
public class Wallet extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(unique = true)
    @Positive
    private Long walletNumber;

    @Column
    @NotBlank(message = "must be not empty")
    private String name;
    @PositiveOrZero
    @Digits(fraction = 2,integer = 16)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private WalletStatus walletStatus;
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "wallet")
    private List<Transaction> transactions;

    public Wallet() {
        this.walletStatus = WalletStatus.ENABLED;
        this.transactions = new ArrayList<>();
    }

    public Wallet(Long id, Long walletNumber, String name, BigDecimal balance, WalletStatus walletStatus, User user, List<Transaction> transactions) {
        this.id = id;
        this.walletNumber = walletNumber;
        this.name = name;
        this.balance = balance;
        this.walletStatus = walletStatus;
        this.user = user;
        this.transactions = transactions;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
