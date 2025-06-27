package com.DuckVest.Controllers;

import com.DuckVest.DTOs.PortfolioDTO;
import com.DuckVest.Models.Portfolio;
import com.DuckVest.Services.PortfolioServices.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portfolios")
@CrossOrigin(origins = "*")
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

    @GetMapping("/get-reserved-balance={id}")
    public Double getReservedBalance(@PathVariable Long id) {
        return portfolioService.getReservedBalance(id);
    }

    @GetMapping("/get-portfolio-info={portfolioId}")
    public PortfolioDTO getPortfolioInformantion(@PathVariable Long portfolioId) {
        return portfolioService.createPortfolioDTO(portfolioId);
    }

    @PostMapping("/add")
    public void addPortfolio(@RequestBody Portfolio portfolio) {
        portfolioService.savePortfolio(portfolio);
    }

}
