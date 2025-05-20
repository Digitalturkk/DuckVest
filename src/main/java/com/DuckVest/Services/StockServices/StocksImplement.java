package com.DuckVest.Services.StockServices;

import com.DuckVest.CustomEnums.OrderStatus;
import com.DuckVest.CustomEnums.OrderType;
import com.DuckVest.DTOs.OrderDTO;
import com.DuckVest.DTOs.StockDTO;
import com.DuckVest.Models.Orders;
import com.DuckVest.Models.Portfolio;
import com.DuckVest.Models.Stocks;
import com.DuckVest.Repositories.StocksRepo;
import com.DuckVest.Services.OrdersServices.OrderService;
import com.DuckVest.Services.PortfolioServices.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class StocksImplement implements StockService {

    @Autowired
    StocksRepo stocksRepo;
    @Autowired
    PortfolioService portfolioService;
    @Autowired
    OrderService orderService;

    @Override
    public void saveStocks(Stocks stock) {
        stocksRepo.save(stock);
    }

    @Override
    public void deleteStocks(Long id) {
        stocksRepo.deleteById(id);
    }

    @Override
    public Stocks getStockById(Long id) {
        return stocksRepo.findById(id).get();
    }

    @Override
    public List<Stocks> getAllStocks() {
        return stocksRepo.findAll();
    }

    @Override
    public StockDTO createStockDTO(Long id) {
        Stocks stock = stocksRepo.findById(id).get();
        StockDTO stockDTO = new StockDTO();
        stockDTO.setStockID(stock.getId());
        stockDTO.setCompanyName(stock.getCompanyName());
        stockDTO.setStockExchange(stock.getStockExchange());
        stockDTO.setCurrency(stock.getCurrency());
        stockDTO.setPrice(stock.getPrice());
        stockDTO.setAsk(stock.getAsk());
        stockDTO.setBid(stock.getBid());
        return stockDTO;
    }

    @Override
    public OrderDTO buyStock(Long portfolioId, Long stockId, Double quantity, Double brokerFee) {
        Stocks stock = stocksRepo.findById(stockId).get();
        Portfolio portfolio = portfolioService.getPortfolioById(portfolioId);

        List<Stocks> stocksList = portfolio.getStocksList();

        Orders order = new Orders();
        order.setDate(Date.from(Instant.now()));
        order.setStockPrice(stock.getBid());
        order.setQuantity(quantity);
        order.setOrderType(OrderType.BUY);
        order.setInvestor(portfolio.getInvestor());
        order.setStock(stock);
        order.setBrokerFee(brokerFee);
        order.setTotalPrice(stock.getBid() * quantity + brokerFee);
        order.setExecutionType("BUY");

        if (portfolio.getAvailableBalance() >= stock.getBid() * quantity) {
            portfolio.setAvailableBalance(portfolio.getAvailableBalance() - stock.getBid() * quantity);
            stocksList.add(stock);
            System.out.println(stocksList);
            portfolio.setStocksList(stocksList);
            portfolio.setReservedBalance(portfolio.getReservedBalance() + stock.getBid() * quantity);
            portfolio.setAvailableBalance(portfolio.getAvailableBalance() - stock.getBid() * quantity - brokerFee);
            portfolio.setTotalBalance(portfolio.getTotalBalance() - stock.getBid() * quantity - brokerFee);
            portfolioService.getTotalBalance(portfolioId); // Updating total balance
            portfolioService.updatePortfolio(portfolio);

            order.setOrderMessage("Order completed successfully!");
            order.setOrderStatus(OrderStatus.COMPLETED);
            order.setExecutionType("BUY");
            order.setThereExecution(false);

        }
        else {
            order.setOrderMessage("Not enough money on account!");
            order.setOrderStatus(OrderStatus.CANCELLED);
            order.setThereExecution(true);
        }
        order.setPortfolio(portfolio);
        orderService.saveOrders(order);

        return orderService.createOrderDTO(order.getId(), portfolio.getInvestor().getId(), stockId, portfolioId.intValue());
    }
}
