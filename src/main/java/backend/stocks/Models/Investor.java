package backend.stocks.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Investor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Name is required")
    @Min(3)
    private String password;
    @NotBlank(message = "Mail is required")
    private String email;
    @NotBlank(message = "Phone number is required")
    private String phone;
    @NotBlank(message = "Address is required")
    private String address;
    @NotBlank(message = "Account need to be Investor or Trader")
    private String accountType;
    @NotNull
    private Boolean isInvestorAccountEnable;

    // Work with Portfolio

    public Investor() {}

    public Investor(String name, String password, String email, String phone, String address, String accountType, Boolean isInvestorAccountEnable) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.accountType = accountType;
        this.isInvestorAccountEnable = isInvestorAccountEnable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Name is required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is required") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Name is required") @Min(3) String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Name is required") @Min(3) String password) {
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

    public @NotBlank(message = "Account need to be Investor or Trader") String getAccountType() {
        return accountType;
    }

    public void setAccountType(@NotBlank(message = "Account need to be Investor or Trader") String accountType) {
        this.accountType = accountType;
    }

    public @NotNull Boolean getInvestorAccountEnable() {
        return isInvestorAccountEnable;
    }

    public void setInvestorAccountEnable(@NotNull Boolean investorAccountEnable) {
        isInvestorAccountEnable = investorAccountEnable;
    }
}
