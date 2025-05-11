package com.DuckVest.Models;

import com.DuckVest.CustomEnums.AccountType;
import jakarta.persistence.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "investors")
public class Investor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Name is required")
    @Size(min=3)
    private String password;
    @NotBlank(message = "Mail is required")
    @Column(unique = true)
    private String email;
    @NotNull
    @Min(18)
    private int age;
    @NotBlank(message = "Phone number is required")
    private String phone;
    @NotBlank(message = "Address is required")
    private String address;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Account need to be Investor or Trader")
    private AccountType accountType;
    @NotNull
    private Boolean isInvestorAccountEnable;

    @OneToMany(mappedBy = "investor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Portfolio> portfolios;


    // Work with Portfolio

    public Investor() {}

    public Investor(String name, String password, String email, String phone, String address, AccountType accountType, Boolean isInvestorAccountEnable, int age, List<Portfolio> portfolios) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.accountType = accountType;
        this.isInvestorAccountEnable = isInvestorAccountEnable;
        this.portfolios = portfolios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Min(18)
    public int getAge() {
        return age;
    }

    public void setAge(@NotNull @Min(18) int age) {
        this.age = age;
    }

    public @NotBlank(message = "Name is required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is required") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Name is required") @Size(min = 3) String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Name is required") @Size(min = 3) String password) {
        this.password = password;
    }

    public @NotBlank(message = "Mail is required") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Mail is required") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Phone number is required") String getPhone() {
        return phone;
    }

    public void setPhone(@NotBlank(message = "Phone number is required") String phone) {
        this.phone = phone;
    }

    public @NotBlank(message = "Address is required") String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank(message = "Address is required") String address) {
        this.address = address;
    }

    public @NotBlank(message = "Account need to be Investor or Trader") AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(@NotBlank(message = "Account need to be Investor or Trader") AccountType accountType) {
        this.accountType = accountType;
    }

    public @NotNull Boolean getIsInvestorAccountEnable() {
        return isInvestorAccountEnable;
    }

    public void setIsInvestorAccountEnable(@NotNull Boolean investorAccountEnable) {
        isInvestorAccountEnable = investorAccountEnable;
    }

    public List<Portfolio> getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(List<Portfolio> portfolios) {
        this.portfolios = portfolios;
    }
}
