package com.DuckVest.DTOs;

// StockExchangeSummaryDTO service is in com.DuckVest.Services.StockExchangeService
// This DTO is mostly used for summarizing stock exchange data for StockDTOs

public class StockExchangeSummaryDTO {
    private Long id;
    private String name;
    private String country;

    public StockExchangeSummaryDTO() {}

    public StockExchangeSummaryDTO(Long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
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
}
