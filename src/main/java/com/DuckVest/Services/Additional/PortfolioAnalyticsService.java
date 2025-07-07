package com.DuckVest.Services.Additional;

import com.DuckVest.DTOs.AnalyticalDTOs.IndustryDiversificationDTO;
import com.DuckVest.DTOs.AnalyticalDTOs.StockDiversificationDTO;
import com.DuckVest.Models.Investor;
import com.DuckVest.Models.Portfolio;
import com.DuckVest.Models.PortfolioStocks;
import com.DuckVest.Services.InvestorServices.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// We are using this in PortfolioController to calculate industry diversification
@Service
public class PortfolioAnalyticsService {

    @Autowired
    private InvestorService investorService;

    @Transactional
    public IndustryDiversificationDTO calculateIndustryDiversification(Long investorId) {
        Investor investor = investorService.getInvestorById(investorId);
        Portfolio portfolio = investor.getPortfolio();
        List<PortfolioStocks> stocks = portfolio.getPortfolioStocks();

        if (stocks == null || stocks.isEmpty()) {
            return new IndustryDiversificationDTO(Map.of(), 0.0, "No diversification (empty portfolio)");
        }

        double totalValue = stocks.stream()
                .mapToDouble(ps -> ps.getQuantity() * ps.getStock().getPrice())
                .sum();

        Map<String, Double> industryMap = new HashMap<>();

        for (PortfolioStocks ps : stocks) {
            String industry = ps.getStock().getIndustry().trim();
            double value = ps.getQuantity() * ps.getStock().getPrice();
            industryMap.put(industry, industryMap.getOrDefault(industry, 0.0) + value);
        }

        // Convert to percentage
        Map<String, Double> industryToPercentage = new HashMap<>();
        for (Map.Entry<String, Double> entry : industryMap.entrySet()) {
            double percentage = (entry.getValue() / totalValue) * 100;
            industryToPercentage.put(entry.getKey(), Math.round(percentage * 100.0) / 100.0);
        }

        double hhi = industryToPercentage.values().stream()
                .mapToDouble(p -> Math.pow(p / 100.0, 2))
                .sum();

        double diversificationScore = 1 - hhi;
        String level;

        if (diversificationScore >= 0.9) {
            level = "Excellent";
        } else if (diversificationScore >= 0.6) {
            level = "Good";
        } else if (diversificationScore >= 0.3) {
            level = "Medium";
        } else {
            level = "High Risk";
        }

        return new IndustryDiversificationDTO(industryToPercentage, diversificationScore, level);
    }

    @Transactional
    public StockDiversificationDTO getDiversificationByStock(Long investorId) {
        Investor investor = investorService.getInvestorById(investorId);
        Portfolio portfolio = investor.getPortfolio();
        List<PortfolioStocks> stocks = portfolio.getPortfolioStocks();

        if (stocks == null || stocks.isEmpty()) {
            return new StockDiversificationDTO(Map.of(), 0.0, "No diversification (empty portfolio)");
        }

        double totalValue = stocks.stream()
                .mapToDouble(ps -> ps.getQuantity() * ps.getStock().getPrice())
                .sum();

        Map<String, Double> stockMap = new HashMap<>();

        for (PortfolioStocks ps : stocks) {
            String symbol = ps.getStock().getSymbol().trim();
            double value = ps.getQuantity() * ps.getStock().getPrice();
            stockMap.put(symbol, stockMap.getOrDefault(symbol, 0.0) + value);
        }

        // Convert to percentage
        Map<String, Double> stockToPercentage = new HashMap<>();
        for (Map.Entry<String, Double> entry : stockMap.entrySet()) {
            double percentage = (entry.getValue() / totalValue) * 100;
            stockToPercentage.put(entry.getKey(), Math.round(percentage * 100.0) / 100.0);
        }

        double hhi = stockToPercentage.values().stream()
                .mapToDouble(p -> Math.pow(p / 100.0, 2))
                .sum();

        double diversificationScore = 1 - hhi;
        String level;

        if (diversificationScore >= 0.9) {
            level = "Excellent";
        } else if (diversificationScore >= 0.6) {
            level = "Good";
        } else if (diversificationScore >= 0.3) {
            level = "Medium";
        } else {
            level = "High Risk";
        }

        return new StockDiversificationDTO(stockToPercentage, diversificationScore, level);
    }
}
