package com.example.digiwallet.model.user.entity;

import com.example.digiwallet.model.Audit;
import com.example.digiwallet.model.ValidationGroups;
import com.example.digiwallet.model.wallet.entity.Wallet;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table
public class User extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @NotBlank(message = "must be not empty")
    @Size(min = 3, max = 20)
    private String username;
    @NotBlank(message = "must be not empty",groups = ValidationGroups.class)
    private String firstName;
    @NotBlank(message = "must be not empty",groups = ValidationGroups.class)
    private String lastName;

    @OneToMany(mappedBy = "user")
    private List<Wallet> wallets;

    public User(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {

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

    public List<Wallet> getWallets() {
        return wallets;
    }

    public void setWallets(List<Wallet> wallets) {
        this.wallets = wallets;
    }
}
