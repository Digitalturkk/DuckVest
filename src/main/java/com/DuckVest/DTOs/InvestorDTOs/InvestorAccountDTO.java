package com.DuckVest.DTOs.InvestorDTOs;

import com.DuckVest.CustomEnums.AccountType;

// investorAccountDTOs service is in com.DuckVest.Services.InvestorAccountService
public class InvestorAccountDTO {
    private String username;
    private String name;
    private String email;
    private String phone;
    private AccountType AccountType;
    private Double PortfolioBalance;
    private Double BuyingPower;

    public InvestorAccountDTO(String username,String name, String email, String phone, AccountType accountType, Double portfolioBalance, Double buyingPower) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.AccountType = accountType;
        this.PortfolioBalance = portfolioBalance;
        this.BuyingPower = buyingPower;
    }

    public InvestorAccountDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public com.DuckVest.CustomEnums.AccountType getAccountType() {
        return AccountType;
    }

    public void setAccountType(com.DuckVest.CustomEnums.AccountType accountType) {
        this.AccountType = accountType;
    }

    public Double getPortfolioBalance() {
        return PortfolioBalance;
    }

    public void setPortfolioBalance(Double portfolioBalance) {
        this.PortfolioBalance = portfolioBalance;
    }

    public Double getBuyingPower() {
        return BuyingPower;
    }

    public void setBuyingPower(Double buyingPower) {
        this.BuyingPower = buyingPower;
    }

}
