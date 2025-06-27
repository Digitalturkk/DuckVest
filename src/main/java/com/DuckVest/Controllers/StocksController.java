package com.DuckVest.Controllers;

import com.DuckVest.DTOs.OrderDTO;
import com.DuckVest.Models.Stocks;
import com.DuckVest.Services.PortfolioStocksServices.PortfolioStocksService;
import com.DuckVest.Services.StockServices.StocksService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
@CrossOrigin(origins = "*")
public class StocksController {

    @Autowired
    StocksService stocksService;
    @Autowired
    PortfolioStocksService portfolioStocksService;

    @GetMapping("/all")
    public List<Stocks> getAllStocks() {
        return stocksService.getAllStocks();
    }

    @GetMapping("/{id}")
    public Stocks getStocks(@PathVariable Long id) {
        return stocksService.getStockById(id);
    }

    @PostMapping("/add")
    public void addStocks(@RequestBody Stocks stocks) {
        stocksService.saveStocks(stocks);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStocks(@PathVariable Long id) {
        stocksService.deleteStocks(id);
    }

    @PostMapping("/buy-stk={stockId}-prtfl={portfolioId}-qnt={quantity}-fee={brokerFee}")
    public OrderDTO buyStock(@PathVariable Long portfolioId, @PathVariable Long stockId, @PathVariable Double quantity, @PathVariable Double brokerFee) throws MessagingException {
        return portfolioStocksService.buyStock(portfolioId, stockId, quantity, brokerFee);
    }

    @PostMapping("/sell-stk={stockId}-prtfl={portfolioId}-qnt={quantity}-fee={brokerFee}")
    public OrderDTO sellStock(@PathVariable Long portfolioId, @PathVariable Long stockId, @PathVariable Double quantity, @PathVariable Double brokerFee) throws MessagingException {
        return portfolioStocksService.sellStock(portfolioId, stockId, quantity, brokerFee);
    }
}
