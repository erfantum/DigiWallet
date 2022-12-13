package com.example.digiwallet.model.user.dto;

import com.example.digiwallet.model.ValidationGroups;
import com.example.digiwallet.model.wallet.dto.WalletDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


public class UserDto {
    private Long id;
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    @NotBlank(groups = ValidationGroups.class)
    private String firstName;
    @NotBlank(groups = ValidationGroups.class)
    private String lastName;

    private Date createDate;

    private Date updateDate;

    private List<WalletDto> wallets;

    public UserDto() {
    }

    public UserDto(Long id, String username, String firstName, String lastName, Date createDate, Date updateDate, List<WalletDto> wallets) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.wallets = wallets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public List<WalletDto> getWallets() {
        return wallets;
    }

    public void setWallets(List<WalletDto> wallets) {
        this.wallets = wallets;
    }
}