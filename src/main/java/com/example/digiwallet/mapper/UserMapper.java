package com.example.digiwallet.mapper;

import com.example.digiwallet.model.user.dto.UserDto;
import com.example.digiwallet.model.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User dtoToEntity(UserDto userDto);

    UserDto entityToDto(User user);

    List<User> dtoListToEntityList(List<UserDto> userDtoList);

    List<UserDto> EntityListToDtoList(List<User> users);
}
