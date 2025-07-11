package com.DuckVest.DTOs.InvestorDTOs;

// InvestorSummaryDTOs service is in com.DuckVest.Services.InvestorService
public class InvestorSummaryDTO {
    private String profilePictureUrl;
    private String name;
    private String email;
    private String phone;

    public InvestorSummaryDTO(String name, String email, String phone, String profilePictureUrl) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.profilePictureUrl = profilePictureUrl;
    }

    public InvestorSummaryDTO() {
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

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }
}
