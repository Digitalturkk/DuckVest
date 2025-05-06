package backend.stocks.Services.PortfolioServices;

import backend.stocks.Models.Portfolio;

import java.util.List;

public interface PortfolioService {
    List<Portfolio> getAllPortfolios();
    Portfolio getPortfolioById(Long id);
    void savePortfolio(Portfolio portfolio);
    Double getTotalBalance(Long id);
    Double getReservedBalance(Long id);
}
