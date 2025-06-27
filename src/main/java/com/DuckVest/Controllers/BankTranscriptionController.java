package com.DuckVest.Controllers;

import com.DuckVest.DTOs.BankMoneyTransactionDTO;
import com.DuckVest.Services.PortfolioServices.PortfolioService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class BankTranscriptionController {

    @Autowired
    PortfolioService portfolioService;

    @PostMapping("/add-money-amount={amount}-investorid={id}")
    public BankMoneyTransactionDTO addMoneyToBankAccount(@PathVariable Double amount, @PathVariable Long id) throws MessagingException {
        return portfolioService.addMoneyToPortfolio(id, amount);
    }
}
