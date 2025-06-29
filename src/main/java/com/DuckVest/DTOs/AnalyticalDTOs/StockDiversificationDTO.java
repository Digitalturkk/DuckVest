package com.DuckVest.DTOs.AnalyticalDTOs;

import java.util.Map;

public class StockDiversificationDTO {
    private Map<String, Double> stockDistribution;
    private double diversificationScore;
    private String level;

    public StockDiversificationDTO(Map<String, Double> stockDistribution, double diversificationScore, String level) {
        this.stockDistribution = stockDistribution;
        this.diversificationScore = diversificationScore;
        this.level = level;
    }

    public StockDiversificationDTO() {
    }

    public Map<String, Double> getStockDistribution() {
        return stockDistribution;
    }

    public void setStockDistribution(Map<String, Double> stockDistribution) {
        this.stockDistribution = stockDistribution;
    }

    public double getDiversificationScore() {
        return diversificationScore;
    }

    public void setDiversificationScore(double diversificationScore) {
        this.diversificationScore = diversificationScore;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}

