package com.DuckVest.Controllers;

import com.DuckVest.DTOs.OrderDTO;
import com.DuckVest.Models.Stocks;
import com.DuckVest.Services.PortfolioStocksServices.PortfolioStocksService;
import com.DuckVest.Services.StockServices.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StocksController {

    @Autowired
    StockService stockService;
    @Autowired
    PortfolioStocksService portfolioStocksService;

    @GetMapping("/all")
    public List<Stocks> getAllStocks() {
        return stockService.getAllStocks();
    }

    @GetMapping("/{id}")
    public Stocks getStocks(@PathVariable Long id) {
        return stockService.getStockById(id);
    }

    @PostMapping("/add")
    public void addStocks(@RequestBody Stocks stocks) {
        stockService.saveStocks(stocks);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStocks(@PathVariable Long id) {
        stockService.deleteStocks(id);
    }

    @PostMapping("/buy-stk={stockId}-prtfl={portfolioId}-qnt={quantity}-fee={brokerFee}")
    public OrderDTO buyStock(@PathVariable Long portfolioId, @PathVariable Long stockId, @PathVariable Double quantity, @PathVariable Double brokerFee) {
        return portfolioStocksService.buyStock(portfolioId, stockId, quantity, brokerFee);
    }

    @PostMapping("/sell-stk={stockId}-prtfl={portfolioId}-qnt={quantity}-fee={brokerFee}")
    public OrderDTO sellStock(@PathVariable Long portfolioId, @PathVariable Long stockId, @PathVariable Double quantity, @PathVariable Double brokerFee) {
        return portfolioStocksService.sellStock(portfolioId, stockId, quantity, brokerFee);
    }
}
