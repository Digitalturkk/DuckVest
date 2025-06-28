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
    public List<PortfolioDTO> getAllPortfolios() {
        return portfolioService.createAllPortfoliosDTOs();
    }

    @GetMapping("/get-id={id}")
    public PortfolioDTO getPortfolioById(@PathVariable Long id) {
        return portfolioService.createPortfolioDTO(id);
    }

    @GetMapping("/get-reserved-balance={id}")
    public Double getReservedBalance(@PathVariable Long id) {
        return portfolioService.getReservedBalance(id);
    }

}
