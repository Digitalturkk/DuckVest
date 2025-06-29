package com.DuckVest.DTOs.AnalyticalDTOs;

import java.util.Map;

public class IndustryDiversificationDTO {
    private Map<String, Double> industryToPercentage;
    private double diversificationScore;
    private String diversificationLevel;

    public IndustryDiversificationDTO(Map<String, Double> industryToPercentage,
                                      double diversificationScore,
                                      String diversificationLevel) {
        this.industryToPercentage = industryToPercentage;
        this.diversificationScore = diversificationScore;
        this.diversificationLevel = diversificationLevel;
    }

    public Map<String, Double> getIndustryToPercentage() {
        return industryToPercentage;
    }

    public double getDiversificationScore() {
        return diversificationScore;
    }

    public String getDiversificationLevel() {
        return diversificationLevel;
    }
}
