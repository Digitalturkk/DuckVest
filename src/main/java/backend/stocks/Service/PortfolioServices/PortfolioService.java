package backend.stocks.Service.PortfolioServices;

import backend.stocks.Models.Portfolio;

import java.util.List;

public interface PortfolioService {
    List<Portfolio> getAllPortfolios();
    Portfolio getPortfolioById(Long id);
    void savePortfolio(Portfolio portfolio);
    int getTotalBalance(Long id);
    int getReservedBalance(Long id);
}
