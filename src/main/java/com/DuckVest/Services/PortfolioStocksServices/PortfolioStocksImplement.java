package com.DuckVest.Services.PortfolioStocksServices;

import com.DuckVest.CustomEnums.OrderStatus;
import com.DuckVest.CustomEnums.OrderType;
import com.DuckVest.DTOs.OrderDTO;
import com.DuckVest.DTOs.PortfolioStocksDTO;
import com.DuckVest.DTOs.StockDTO;
import com.DuckVest.DTOs.StockExchangeSummaryDTO;
import com.DuckVest.Exceptions.GlobalNotFound.GlobalNotFoundException;
import com.DuckVest.Models.Orders;
import com.DuckVest.Models.Portfolio;
import com.DuckVest.Models.PortfolioStocks;
import com.DuckVest.Models.Stocks;
import com.DuckVest.Repositories.PortfolioStocksRepo;
import com.DuckVest.Services.ExchangeServices.StockExchangeService;
import com.DuckVest.Services.OrdersServices.OrderService;
import com.DuckVest.Services.PortfolioServices.PortfolioService;
import com.DuckVest.Services.StockServices.StocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PortfolioStocksImplement implements PortfolioStocksService {

    @Autowired
    PortfolioStocksRepo portfolioStocksRepo;
    @Autowired
    StocksService stocksService;
    @Autowired
    PortfolioService portfolioService;
    @Autowired
    OrderService orderService;
    @Autowired
    StockExchangeService stockExchangeService;

    @Override
    public List<PortfolioStocks> getAllPortfolioStocks() {
        return portfolioStocksRepo.findAll();
    }

    @Override
    public PortfolioStocks getPortfolioStockById(Long id) {
        return portfolioStocksRepo.findById(id).orElseThrow( () -> new GlobalNotFoundException("Portfolio-Stock not found with id: " + id, null));
    }

    @Override
    public void savePortfolioStock(PortfolioStocks portfolioStock) {
        portfolioStocksRepo.save(portfolioStock);
    }

    @Override
    public void deletePortfolioStock(Long id) {
        if (!portfolioStocksRepo.existsById(id)) {
            throw new GlobalNotFoundException("Portfolio-Stock not found with id: " + id, null);
        }
        portfolioStocksRepo.deleteById(id);
    }

    @Override
    public void updatePortfolioStock(PortfolioStocks portfolioStock) {
        PortfolioStocks existingPortfolioStock = portfolioStocksRepo.findById(portfolioStock.getId()).orElseThrow( () -> new GlobalNotFoundException("Portfolio-Stock not found with id: " + portfolioStock.getId(), null));

        existingPortfolioStock.setQuantity(portfolioStock.getQuantity());
        existingPortfolioStock.setTotalCost(portfolioStock.getTotalCost());
        existingPortfolioStock.setAveragePrice(portfolioStock.getAveragePrice());
        existingPortfolioStock.setPortfolio(portfolioStock.getPortfolio());
        existingPortfolioStock.setStock(portfolioStock.getStock());
        portfolioStocksRepo.save(existingPortfolioStock);

    }

    @Override
    public PortfolioStocksDTO createPortfolioStocksDTO(PortfolioStocks portfolioStocks) {
        Stocks stock = portfolioStocks.getStock();
        StockExchangeSummaryDTO stockExchangeSummaryDTO = stockExchangeService.createStockExchangeSummaryDTO(stock.getStockExchange());

        return new PortfolioStocksDTO(
                portfolioStocks.getId(),
                portfolioStocks.getQuantity(),
                portfolioStocks.getAveragePrice(),
                portfolioStocks.getTotalCost(),
                new StockDTO(stock.getId(), stock.getCompanyName(), stock.getCurrency(), stockExchangeSummaryDTO, stock.getPrice(), stock.getAsk(), stock.getBid())
        );
    }

    @Override
    public OrderDTO buyStock(Long portfolioId, Long stockId, Double quantity, Double brokerFee) {

        if (quantity <= 0 || brokerFee < 0) {
            throw new IllegalArgumentException("Invalid quantity or broker fee");
        }

        Stocks stock = stocksService.getStockById(stockId);
        Portfolio portfolio = portfolioService.getPortfolioById(portfolioId);

        Double stockPrice = stock.getAsk();
        Double availableBalance = portfolio.getAvailableBalance();

        Double stockCost = stockPrice * quantity;
        Double totalPrice = stockCost + brokerFee;

        Optional<PortfolioStocks> portfolioStock = portfolioStocksRepo.findByPortfolioAndStock(portfolio, stock);

        Orders order = new Orders();
        order.setDate(Date.from(Instant.now()));
        order.setStockPrice(stockPrice);
        order.setQuantity(quantity);
        order.setOrderType(OrderType.BUY);
        order.setInvestor(portfolio.getInvestor());
        order.setStock(stock);
        order.setBrokerFee(brokerFee);
        order.setTotalPrice(totalPrice);
        order.setExecutionType("BUY");

        if (portfolioStock.isEmpty() && availableBalance >= totalPrice) {
            PortfolioStocks newPortfolioStock = new PortfolioStocks();
            newPortfolioStock.setQuantity(quantity);
            newPortfolioStock.setPortfolio(portfolio);
            newPortfolioStock.setStock(stock);
            newPortfolioStock.setTotalCost(stockCost);
            newPortfolioStock.setAveragePrice(stockPrice);
            portfolioStocksRepo.save(newPortfolioStock);

            processSuccessfulBuyOrder(portfolioId, quantity, brokerFee, stock, portfolio, order, newPortfolioStock);
        } else if (portfolioStock.isPresent() && portfolio.getAvailableBalance() >= totalPrice) {

            PortfolioStocks existingPortfolioStock = portfolioStock.get();
            existingPortfolioStock.setQuantity(existingPortfolioStock.getQuantity() + quantity);
            existingPortfolioStock.setTotalCost(existingPortfolioStock.getTotalCost() + stockCost);
            existingPortfolioStock.setAveragePrice(existingPortfolioStock.getTotalCost() / existingPortfolioStock.getQuantity());
            processSuccessfulBuyOrder(portfolioId, quantity, brokerFee, stock, portfolio, order, existingPortfolioStock);
        } else {
            order.setOrderMessage("Not enough money on account!");
            order.setOrderStatus(OrderStatus.CANCELLED);
            order.setThereExecution(true);
        }

        order.setPortfolio(portfolio);
        orderService.saveOrders(order);

        return orderService.createOrderDTO(order.getId(), portfolio.getInvestor().getId(), stockId);
    }

    @Override
    public OrderDTO sellStock(Long portfolioId, Long stockId, Double quantity, Double brokerFee) {

        if (quantity <= 0 || brokerFee < 0) {
            throw new IllegalArgumentException("Invalid quantity or broker fee");
        }

        Stocks stock = stocksService.getStockById(stockId);
        Portfolio portfolio = portfolioService.getPortfolioById(portfolioId);

        Double stockPrice = stock.getBid();

        Double stockCost = stockPrice * quantity;
        Double totalPrice  = stockCost - brokerFee;

        Optional<PortfolioStocks> portfolioStock = portfolioStocksRepo.findByPortfolioAndStock(portfolio, stock);

        Orders order = new Orders();
        order.setDate(Date.from(Instant.now()));
        order.setStockPrice(stockPrice);
        order.setQuantity(quantity);
        order.setOrderType(OrderType.SELL);
        order.setInvestor(portfolio.getInvestor());
        order.setStock(stock);
        order.setBrokerFee(brokerFee);
        order.setTotalPrice(totalPrice);
        order.setExecutionType("SELL");

        if (portfolioStock.isEmpty()) {
            order.setOrderMessage("You don't have this stock in your portfolio!");
            order.setOrderStatus(OrderStatus.CANCELLED);
            order.setThereExecution(true);

        } else if (portfolioStock.get().getQuantity() >= quantity) {

            PortfolioStocks updatedPortfolioStock = portfolioStock.get();
            updatedPortfolioStock.setQuantity(updatedPortfolioStock.getQuantity() - quantity);
            updatedPortfolioStock.setTotalCost(updatedPortfolioStock.getTotalCost() - stockCost);

            if (updatedPortfolioStock.getQuantity() > 0) {
                updatedPortfolioStock.setAveragePrice(updatedPortfolioStock.getTotalCost() / updatedPortfolioStock.getQuantity());
            } else {
                updatedPortfolioStock.setAveragePrice(0.0);
            }

            updatedPortfolioStock.setPortfolio(portfolio);
            updatedPortfolioStock.setStock(stock);
            updatePortfolioStock(updatedPortfolioStock);

            processSuccessfulSellOrder(portfolioId, quantity, brokerFee, stock, portfolio, order, updatedPortfolioStock);

        } else {
            order.setOrderMessage("You don't have enough stocks to sell!");
            order.setOrderStatus(OrderStatus.CANCELLED);
            order.setThereExecution(true);
        }

        order.setPortfolio(portfolio);
        orderService.saveOrders(order);

        return orderService.createOrderDTO(order.getId(), portfolio.getInvestor().getId(), stockId);
    }

    private void processSuccessfulBuyOrder(Long portfolioId, Double quantity, Double brokerFee, Stocks stock, Portfolio portfolio, Orders order, PortfolioStocks existingPortfolioStock) {
        portfolioStocksRepo.save(existingPortfolioStock);

        Double stockAskPrice = stock.getAsk();
        
        portfolio.setAvailableBalance(portfolio.getAvailableBalance() - stockAskPrice * quantity - brokerFee);
        portfolioService.updateTotalBalance(portfolioId); // Updating total balance
        portfolioService.updatePortfolio(portfolio);

        order.setOrderMessage("Stock bought successfully!");
        order.setOrderStatus(OrderStatus.COMPLETED);
        order.setThereExecution(false);
    }

    private void processSuccessfulSellOrder(Long portfolioId, Double quantity, Double brokerFee, Stocks stock, Portfolio portfolio, Orders order, PortfolioStocks existingPortfolioStock) {
        portfolioStocksRepo.save(existingPortfolioStock);

        Double stockBidPrice = stock.getBid();

        portfolio.setAvailableBalance(portfolio.getAvailableBalance() + stockBidPrice * quantity - brokerFee);
        portfolioService.updateTotalBalance(portfolioId); // Updating total balance
        portfolioService.updatePortfolio(portfolio);

        order.setOrderMessage("Stock sold successfully!");
        order.setOrderStatus(OrderStatus.COMPLETED);
        order.setThereExecution(false);
    }
}
