package com.DuckVest.Services.StockServices;

import com.DuckVest.Models.Portfolio;
import com.DuckVest.Models.Stocks;
import com.DuckVest.Repositories.StocksRepo;
import com.DuckVest.Services.PortfolioServices.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StocksImplement implements StockService {

    @Autowired
    StocksRepo stocksRepo;
    @Autowired
    PortfolioService portfolioService;

    @Override
    public void saveStocks(Stocks stock) {
        stocksRepo.save(stock);
    }

    @Override
    public void deleteStocks(Long id) {
        stocksRepo.deleteById(id);
    }

    @Override
    public Stocks getStocks(Long id) {
        return stocksRepo.findById(id).get();
    }

    @Override
    public List<Stocks> getAllStocks() {
        return stocksRepo.findAll();
    }

    @Override
    public String buyStock(Long portfolioId, Long stockId, int quantity) {
        Stocks stock = stocksRepo.findById(stockId).get();
        Portfolio portfolio = portfolioService.getPortfolioById(portfolioId);
        List<Stocks> stocksList = portfolio.getStocksList();
        if (portfolio.getTotalBalance() >= stock.getBid() * quantity) {
            portfolio.setAvailableBalance(portfolio.getAvailableBalance() - stock.getBid() * quantity);
            stocksList.add(stock);
            portfolio.setStocksList(stocksList);
            portfolioService.getTotalBalance(portfolioId);
            portfolioService.savePortfolio(portfolio);
            String bill = "Stock: " + stock.getCompanyName() + "bought successfully! \n" + "Quantity: " + quantity + "\n" + "Total price: " + stock.getBid() * quantity + "\n" + "Available balance: " + portfolio.getAvailableBalance() + "\n";
            return bill;
        }
        else {
            String bill = "Not enough money on your account! \n" + "Available balance: " + portfolio.getAvailableBalance() + "\n" + "Stock price: " + stock.getBid() * quantity + "\n";
            return bill;
        }
    }
}
