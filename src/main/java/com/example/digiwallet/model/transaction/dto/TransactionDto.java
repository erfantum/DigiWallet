package com.example.digiwallet.model.transaction.dto;

import com.example.digiwallet.model.ValidationGroups;
import com.example.digiwallet.model.transaction.entity.TransactionStatus;
import com.example.digiwallet.model.transaction.entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;


public class TransactionDto {
    private Long id;
    @Positive
    @Digits(fraction = 2,integer = 7)
    private BigDecimal amount;
    @Digits(fraction = 2,integer = 16)
    private Long source;
    @Digits(fraction = 2,integer = 16)
    private Long destination;

    private Date createDate;

    private TransactionStatus transactionStatus;

    private TransactionType transactionType;

    public TransactionDto() {
    }

    public TransactionDto(Long id, BigDecimal amount, Long source, Long destination, Date createDate, TransactionStatus transactionStatus, TransactionType transactionType) {
        this.id = id;
        this.amount = amount;
        this.source = source;
        this.destination = destination;
        this.createDate = createDate;
        this.transactionStatus = transactionStatus;
        this.transactionType = transactionType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getSource() {
        return source;
    }

    public void setSource(Long source) {
        this.source = source;
    }

    public Long getDestination() {
        return destination;
    }

    public void setDestination(Long destination) {
        this.destination = destination;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
