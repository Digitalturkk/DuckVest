package com.DuckVest.DTOs;

import java.time.ZonedDateTime;

// StockExchangeDTOs service is in com.DuckVest.Services.StockExchangeService
public class StockExchangeDTO {
    private Long id;
    private String name;
    private String country;
    private String city;
    private String currency;
    private Boolean isActive;
    private ZonedDateTime openTime;
    private ZonedDateTime closeTime;

    public StockExchangeDTO() {}

    public StockExchangeDTO(Long id, String name, String country, String city, String currency, Boolean isActive, ZonedDateTime openTime, ZonedDateTime closeTime) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.currency = currency;
        this.isActive = isActive;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public ZonedDateTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(ZonedDateTime openTime) {
        this.openTime = openTime;
    }

    public ZonedDateTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(ZonedDateTime closeTime) {
        this.closeTime = closeTime;
    }
}
