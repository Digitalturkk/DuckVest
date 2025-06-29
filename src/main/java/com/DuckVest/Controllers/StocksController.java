package com.DuckVest.Controllers;

import com.DuckVest.DTOs.OrderDTO;
import com.DuckVest.DTOs.StockDTO;
import com.DuckVest.Models.Stocks;
import com.DuckVest.Services.Additional.StockPriceUpdater;
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
    @Autowired
    StockPriceUpdater stockPriceUpdater;

    @GetMapping("/all")
    public List<StockDTO> getAllStocks() {
        return stocksService.getAllStocksDTO();
    }

    @GetMapping("/{id}")
    public StockDTO getStocks(@PathVariable Long id) {
        return stocksService.createStockDTO(id);
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

    //Updating stock prices manually for all stocks
    @PostMapping("/update-prices")
    public void updateAllPrices() {
        stockPriceUpdater.updateAllPrices();
    }
}
