package com.DuckVest.Services.PortfolioServices;

import com.DuckVest.DTOs.BankMoneyTransactionDTO;
import com.DuckVest.DTOs.PortfolioDTO;
import com.DuckVest.Models.Portfolio;
import jakarta.mail.MessagingException;

import java.util.List;

public interface PortfolioService {
    List<Portfolio> getAllPortfolios();
    Portfolio getPortfolioById(Long id);
    void savePortfolio(Portfolio portfolio);
    void updatePortfolio(Portfolio portfolio);
    void updateTotalBalance(Long id);

    BankMoneyTransactionDTO addMoneyToPortfolio(Long id, Double amount) throws MessagingException;
    Double getReservedBalance(Long id);

    PortfolioDTO createPortfolioDTO(Long portfolioId);
}
