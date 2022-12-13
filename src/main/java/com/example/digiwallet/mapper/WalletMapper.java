package com.example.digiwallet.mapper;

import com.example.digiwallet.model.wallet.dto.WalletDto;
import com.example.digiwallet.model.wallet.entity.Wallet;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WalletMapper {
    Wallet dtoToEntity(WalletDto walletDto);

    WalletDto entityToDto(Wallet wallet);

    List<Wallet> dtoListToEntityList(List<WalletDto> walletDtoList);

    List<WalletDto> EntityListToDtoList(List<Wallet> wallets);
}
