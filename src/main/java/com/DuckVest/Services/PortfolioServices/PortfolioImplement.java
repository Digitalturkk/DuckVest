package com.DuckVest.Services.PortfolioServices;

import com.DuckVest.DTOs.PortfolioDTO;
import com.DuckVest.Models.Investor;
import com.DuckVest.Models.Orders;
import com.DuckVest.Models.Portfolio;
import com.DuckVest.Models.Stocks;
import com.DuckVest.Repositories.InvestorsRepo;
import com.DuckVest.Repositories.PortfolioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioImplement implements PortfolioService {

    @Autowired
    PortfolioRepo portfolioRepo;
    @Autowired
    private InvestorsRepo investorsRepo;

    @Override
    public List<Portfolio> getAllPortfolios() {
        return portfolioRepo.findAll();
    }

    @Override
    public Portfolio getPortfolioById(Long id) {
        return portfolioRepo.findById(id).get();
    }

    @Override
    public void savePortfolio(Portfolio portfolio) {
        portfolioRepo.save(portfolio);
    }

    @Override
    public Double getTotalBalance(Long id) {
        Portfolio workingPortfolio = portfolioRepo.findById(id).get();

        List<Stocks> portfolioStocks = workingPortfolio.getStocksList();
        double totalBalance = 0.0;

        for (Stocks s : portfolioStocks) { // Geting all stocks' bid price, then + available balance on account
            totalBalance += s.getBid();
        }
        totalBalance += workingPortfolio.getAvailableBalance();
        workingPortfolio.setTotalBalance(totalBalance);

        portfolioRepo.save(workingPortfolio);

        return totalBalance;
    }

    @Override
    public Double getReservedBalance(Long id) {
        Portfolio workingPortfolio = portfolioRepo.findById(id).get();

        List<Orders> portfolioOrders = workingPortfolio.getOrdersList();
        double reservedBalance = 0.0;

        for (Orders r : portfolioOrders) {
            if ("IN_PROGRESS".equals(r.getOrderStatus())) { // multiplying stock's quantity to its price
                Double quantity = r.getQuantity();
                Stocks stockPrice = r.getStockPrice();
                reservedBalance += quantity * stockPrice.getBid();
            }
        }
        workingPortfolio.setReservedBalance(reservedBalance);

        portfolioRepo.save(workingPortfolio);

        return reservedBalance;
    }

    @Override
    public PortfolioDTO getPortfolioDTOById(Long portfolioId, Long investorId) {
        Portfolio portfolio = portfolioRepo.findById(portfolioId).get();
        Investor investor = investorsRepo.findById(investorId).get();
        PortfolioDTO portfolioDTO = new PortfolioDTO();
        portfolioDTO.setPortfolioId(portfolio.getPortfolioId());
        portfolioDTO.setInvestorName(investor.getName());
        portfolioDTO.setAvailableBalance(portfolio.getAvailableBalance());
        portfolioDTO.setReservedBalance(portfolio.getReservedBalance());
        portfolioDTO.setTotalBalance(portfolio.getTotalBalance());
        portfolioDTO.setLastUpdate(portfolio.getLastUpdate());
        portfolioDTO.setStocksList(portfolio.getStocksList());
        portfolioDTO.setOrdersList(portfolio.getOrdersList());
        return portfolioDTO;
    }
}
