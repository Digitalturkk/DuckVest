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
    public StockDTO getStockDTO(Long id) {
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

        if (portfolio.getAvailableBalance() >= stock.getBid() * quantity) {
            portfolio.setAvailableBalance(portfolio.getAvailableBalance() - stock.getBid() * quantity);
            stocksList.add(stock);
            System.out.println(stocksList);
            portfolio.setStocksList(stocksList);
            portfolioService.getTotalBalance(portfolioId); // Updating total balance
            portfolioService.updatePortfolio(portfolio);

            Long orderId = stock.getOrders().size() + 1L;

            Orders order = new Orders();
            order.setOrderMessage("Order completed successfully!");
            order.setId(orderId);
            order.setDate(Date.from(Instant.now()));
            order.setOrderStatus(OrderStatus.COMPLETED);
            order.setStockPrice(stock.getBid());
            order.setQuantity(quantity);
            order.setOrderType(OrderType.BUY);
            order.setInvestor(portfolio.getInvestor());
            order.setStock(stock);
            order.setBrokerFee(brokerFee);
            order.setTotalPrice(stock.getBid() * quantity + brokerFee);
            order.setExecutionType("BUY");
            order.setThereExecution(false);
            order.setPortfolio(portfolio);
            orderService.saveOrders(order);

            return orderService.createOrderDTO(orderId, portfolio.getInvestor().getId(), stockId, portfolioId.intValue());
        }
        else {
            Long orderId = stock.getOrders().size() + 1L;

            Orders order = new Orders();
            order.setOrderMessage("Order completed successfully!");
            order.setId(orderId);
            order.setDate(Date.from(Instant.now()));
            order.setOrderStatus(OrderStatus.CANCELLED);
            order.setStockPrice(stock.getBid());
            order.setQuantity(quantity);
            order.setOrderType(OrderType.BUY);
            order.setInvestor(portfolio.getInvestor());
            order.setStock(stock);
            order.setBrokerFee(brokerFee);
            order.setTotalPrice(stock.getBid() * quantity + brokerFee);
            order.setExecutionType("BUY");
            order.setThereExecution(true);
            order.setPortfolio(portfolio);
            orderService.saveOrders(order);

            return orderService.createOrderDTO(orderId, portfolio.getInvestor().getId(), stockId, portfolioId.intValue());
        }
    }
}
