package com.DuckVest.Controllers;

import com.DuckVest.DTOs.PortfolioDTO;
import com.DuckVest.Models.Portfolio;
import com.DuckVest.Services.PortfolioServices.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portfolios")
public class PortfolioController {

    @Autowired
    PortfolioService portfolioService;

    @GetMapping("/get-all")
    public List<Portfolio> getAllPortfolios() {
        return portfolioService.getAllPortfolios();
    }

    @GetMapping("/get-id={id}")
    public Portfolio getPortfolioById(@PathVariable Long id) {
        return portfolioService.getPortfolioById(id);
    }

    @GetMapping("/get-total-balance={id}")
    public Double getTotalBalance(@PathVariable Long id) {
        return portfolioService.getTotalBalance(id);
    }

    @GetMapping("/get-reserved-balance={id}")
    public Double getReservedBalance(@PathVariable Long id) {
        return portfolioService.getReservedBalance(id);
    }

    @GetMapping("/get-available-balance={id}")
    public Double getAvailableBalance(@PathVariable Long id) {
        return portfolioService.getTotalBalance(id) - portfolioService.getReservedBalance(id);
    }

    @GetMapping("/get-portfolio-value={id}")
    public Double getPortfolioValue(@PathVariable Long id) {
        return portfolioService.getTotalBalance(id) + portfolioService.getReservedBalance(id);
    }

    @GetMapping("/get-portfolio-info={portfolioId}&{investorId}")
    public PortfolioDTO getPortfolioInformantion(@PathVariable Long portfolioId, @PathVariable Long investorId) {
        return portfolioService.createPortfolioDTO(portfolioId, investorId, portfolioId);
    }

    @PostMapping("/add")
    public void addPortfolio(@RequestBody Portfolio portfolio) {
        portfolioService.savePortfolio(portfolio);
    }

}
