package com.DuckVest.Controllers;

import com.DuckVest.DTOs.BankMoneyTransactionDTO;
import com.DuckVest.Services.PortfolioServices.PortfolioService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
@CrossOrigin(origins = "*")
public class BankTranscriptionController {

    @Autowired
    PortfolioService portfolioService;

    @PostMapping("/add-money-amount={amount}-investorid={id}")
    public BankMoneyTransactionDTO addMoneyToBankAccount(@PathVariable Double amount, @PathVariable Long id) throws MessagingException {
        return portfolioService.addMoneyToPortfolio(id, amount);
    }
}
