package com.example.digiwallet.mapper;

import com.example.digiwallet.model.transaction.dto.TransactionDto;
import com.example.digiwallet.model.transaction.entity.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    Transaction dtoToEntity(TransactionDto transactionDto);

    TransactionDto entityToDto(Transaction transaction);

    List<Transaction> dtoListToEntityList(List<TransactionDto> transactionDtoList);

    List<TransactionDto> EntityListToDtoList(List<Transaction> transactions);
}
