package com.DuckVest.Services.PortfolioServices;

import com.DuckVest.CustomEnums.OrderStatus;
import com.DuckVest.DTOs.PortfolioDTO;
import com.DuckVest.DTOs.StockDTO;
import com.DuckVest.Models.*;
import com.DuckVest.Repositories.InvestorsRepo;
import com.DuckVest.Repositories.PortfolioRepo;
import com.DuckVest.Repositories.PortfolioStocksRepo;
import com.DuckVest.Services.OrdersServices.OrderService;
import com.DuckVest.Services.PortfolioStocksServices.PortfolioStocksService;
import com.DuckVest.Services.StockServices.StockService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PortfolioImplement implements PortfolioService {

    @Autowired
    PortfolioRepo portfolioRepo;
    @Autowired
    InvestorsRepo investorsRepo;
    @Autowired
    OrderService orderService;
    @Autowired
    PortfolioStocksRepo portfolioStocksRepo;
    @Autowired
    ModelMapper modelMapper;

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
    public PortfolioDTO createPortfolioDTO(Long portfolioId, Long investorId, Long portfolioStocksID) {
        Portfolio portfolio = portfolioRepo.findById(portfolioId).get();
        Investor investor = investorsRepo.findById(investorId).get();

        List<PortfolioStocks> portfolioStocksList = portfolioStocksRepo.findAllByPortfolio(portfolio);

        List<StockDTO> stocksList = new ArrayList<>();

        for (PortfolioStocks ps : portfolioStocksList) {
            Stocks stock = ps.getStock();
            StockDTO stockDTO = new StockDTO();
            stockDTO.setStockID(stock.getId());
            stockDTO.setCompanyName(stock.getCompanyName());
            stockDTO.setCurrency(stock.getCurrency());
            stockDTO.setStockExchange(stock.getStockExchange());
            stockDTO.setPrice(stock.getPrice());
            stockDTO.setAsk(stock.getAsk());
            stockDTO.setBid(stock.getBid());
            stocksList.add(stockDTO);
        }


        PortfolioDTO portfolioDTO = new PortfolioDTO();
        portfolioDTO.setPortfolioId(portfolio.getPortfolioId());
        portfolioDTO.setInvestorName(investor.getName());
        portfolioDTO.setAvailableBalance(portfolio.getAvailableBalance());
        portfolioDTO.setReservedBalance(getReservedBalance(portfolioId));
        portfolioDTO.setTotalBalance(getTotalBalance(portfolioId));
        portfolioDTO.setStocksList(stocksList);
        portfolioDTO.setOrdersList(
                portfolio.getOrdersList().stream()
                        .map(order -> orderService.createOrderDTO(order.getId(), investorId, order.getStock().getId(), portfolioId.intValue()))
                        .toList()
        );

        return portfolioDTO;
    }

}
