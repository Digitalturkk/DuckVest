package com.DuckVest.Services.PortfolioServices;

import com.DuckVest.CustomEnums.OrderStatus;
import com.DuckVest.DTOs.PortfolioDTO;
import com.DuckVest.DTOs.StockDTO;
import com.DuckVest.Models.Investor;
import com.DuckVest.Models.Orders;
import com.DuckVest.Models.Portfolio;
import com.DuckVest.Repositories.InvestorsRepo;
import com.DuckVest.Repositories.PortfolioRepo;
import com.DuckVest.Services.OrdersServices.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioImplement implements PortfolioService {

    @Autowired
    PortfolioRepo portfolioRepo;
    @Autowired
    InvestorsRepo investorsRepo;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    OrderService orderService;

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
    public void updatePortfolio(Portfolio portfolio) {
        Portfolio updatingPortfolio = portfolioRepo.findById(portfolio.getPortfolioId()).get();
        modelMapper.map(updatingPortfolio, portfolio);
    }

    @Override
    public Double getTotalBalance(Long id) {
        Portfolio workingPortfolio = portfolioRepo.findById(id).get();
        double reservedBalance = getReservedBalance(id);

        List<Orders> portfolioOrders = workingPortfolio.getOrdersList();

        double totalBalance = 0.0;

        for (Orders o : portfolioOrders) { // Getting all stocks' bid price, then + available balance on account
            if (o.getOrderStatus() == OrderStatus.COMPLETED) {
                totalBalance += o.getStockPrice() * o.getQuantity();
            }
        }

        totalBalance += workingPortfolio.getAvailableBalance();
        totalBalance += reservedBalance;
        workingPortfolio.setTotalBalance(totalBalance);

        portfolioRepo.save(workingPortfolio);

        return totalBalance;
    }

    @Override
    public Double getReservedBalance(Long id) {
        Portfolio workingPortfolio = portfolioRepo.findById(id).get();

        List<Orders> portfolioOrders = workingPortfolio.getOrdersList();
        double reservedBalance = 0.0;

        for (Orders r : portfolioOrders) { // Getting all stocks' bid price, then + available balance on account
            if (r.getOrderStatus() == OrderStatus.IN_PROGRESS) {
                Double quantity = r.getQuantity();
                Double stockPrice = r.getStockPrice();
                reservedBalance += quantity * stockPrice;
            }
        }

        workingPortfolio.setReservedBalance(reservedBalance);

        portfolioRepo.save(workingPortfolio);

        return reservedBalance;
    }

    @Override
    public PortfolioDTO createPortfolioDTO(Long portfolioId, Long investorId) {
        Portfolio portfolio = portfolioRepo.findById(portfolioId).get();
        Investor investor = investorsRepo.findById(investorId).get();

        PortfolioDTO portfolioDTO = new PortfolioDTO();
        portfolioDTO.setPortfolioId(portfolio.getPortfolioId());
        portfolioDTO.setInvestorName(investor.getName());
        portfolioDTO.setAvailableBalance(portfolio.getAvailableBalance());
        portfolioDTO.setReservedBalance(getReservedBalance(portfolioId));
        portfolioDTO.setTotalBalance(getTotalBalance(portfolioId));
        portfolioDTO.setStocksList(portfolio.getStocksList().stream()
                .map(stock -> new StockDTO(
                        stock.getId(),
                        stock.getCompanyName(),
                        stock.getCurrency(),
                        stock.getStockExchange(),
                        stock.getPrice(),
                        stock.getAsk(),
                        stock.getBid()
                ))
                .toList());
        portfolioDTO.setOrdersList(
                portfolio.getOrdersList().stream()
                        .map(order -> orderService.createOrderDTO(order.getId(), investorId, order.getStock().getId(), portfolioId.intValue()))
                        .toList()
        );

        return portfolioDTO;
    }
}
