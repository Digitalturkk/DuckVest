package com.DuckVest.Services.StockServices;

import com.DuckVest.DTOs.StockDTO;
import com.DuckVest.DTOs.StockExchangeSummaryDTO;
import com.DuckVest.Models.Stocks;
import com.DuckVest.Repositories.StocksRepo;
import com.DuckVest.Services.ExchangeServices.StockExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StocksImplement implements StockService { // Добавь проверку количества акций в портфеле перед продажей!!! 

    @Autowired
    StocksRepo stocksRepo;
    @Autowired
    StockExchangeService stockExchangeService;

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
        StockExchangeSummaryDTO stockExchangeSummaryDTO = stockExchangeService.createStockExchangeSummaryDTO(stock.getStockExchange());

        StockDTO stockDTO = new StockDTO();
        stockDTO.setStockID(stock.getId());
        stockDTO.setCompanyName(stock.getCompanyName());
        stockDTO.setStockExchangeSummaryDTO(stockExchangeSummaryDTO);
        stockDTO.setCurrency(stock.getCurrency());
        stockDTO.setPrice(stock.getPrice());
        stockDTO.setAsk(stock.getAsk());
        stockDTO.setBid(stock.getBid());
        return stockDTO;
    }
}
