package com.DuckVest.Controllers;

import com.DuckVest.DTOs.InvestorAccountDTO;
import com.DuckVest.Models.Investor;
import com.DuckVest.Services.InvestorServices.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/investors")
public class InvestorController {

    @Autowired
    InvestorService investorService;

    @GetMapping("/get-all")
    public List<Investor> getAllInvestors() {
        return investorService.getAllInvestors();
    }

    @GetMapping("/get-id={id}")
    public Investor getInvestorById(@PathVariable Long id) {
        return investorService.getInvestor(id);
    }

    @PostMapping("/add")
    public void addInvestor(@RequestBody Investor investor) {
        investorService.saveInvestor(investor);
    }

    @DeleteMapping("/delete-id={id}")
    public void deleteInvestor(@PathVariable Long id) {
        investorService.deleteInvestor(id);
    }

    // Working with investor account (DTO)

    @GetMapping("/get-account-information-id={investorId}-{portfolioId}")
    public InvestorAccountDTO getInvestorAccountInformation(@PathVariable Long investorId, @PathVariable int portfolioId) {
        return investorService.getInvestorAccountInformation(investorId, portfolioId);
    }
}
