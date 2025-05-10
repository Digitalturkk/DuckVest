package backend.stocks.DTOs;

import backend.stocks.CustomEnums.AccountType;

public class InvestorAccountDTO {
    private String name;
    private String email;
    private String phone;
    private AccountType AccountType;
    private Double PortfolioBalance;
    private Double BuyingPower;
    private boolean isAccountActive;

    public InvestorAccountDTO(String name, String email, String phone, AccountType accountType, Double portfolioBalance, Double buyingPower, boolean isAccountActive) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.AccountType = accountType;
        this.PortfolioBalance = portfolioBalance;
        this.BuyingPower = buyingPower;
        this.isAccountActive = isAccountActive;
    }

    public InvestorAccountDTO() {
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

    public backend.stocks.CustomEnums.AccountType getAccountType() {
        return AccountType;
    }

    public void setAccountType(backend.stocks.CustomEnums.AccountType accountType) {
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

    public boolean getIsAccountActive() {
        return this.isAccountActive;
    }

    public void setIsAccountActive(boolean isAccountActive) {
        this.isAccountActive = isAccountActive;
    }
}
