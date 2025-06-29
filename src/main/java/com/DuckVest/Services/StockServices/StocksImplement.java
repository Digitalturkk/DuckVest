package com.DuckVest.Services.StockServices;

import com.DuckVest.DTOs.StockDTO;
import com.DuckVest.DTOs.StockExchangeDTOs.StockExchangeSummaryDTO;
import com.DuckVest.Exceptions.GlobalNotFound.GlobalNotFoundException;
import com.DuckVest.Models.Stocks;
import com.DuckVest.Repositories.StocksRepo;
import com.DuckVest.Services.Additional.StockPriceUpdater;
import com.DuckVest.Services.ExchangeServices.StockExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StocksImplement implements StocksService { // Добавь проверку количества акций в портфеле перед продажей!!!

    @Autowired
    StocksRepo stocksRepo;
    @Autowired
    StockExchangeService stockExchangeService;
    @Autowired
    StockPriceUpdater stockPriceUpdater;

    @Override
    public void saveStocks(Stocks stock) {
        stocksRepo.save(stock);
    }

    @Override
    public void deleteStocks(Long id) {
        if (!stocksRepo.existsById(id)) {
            throw new GlobalNotFoundException("Stock not found with id: " + id, null);
        }
        stocksRepo.deleteById(id);
    }

    @Override
    public Stocks getStockById(Long id) {
        return stocksRepo.findById(id).orElseThrow(() -> new GlobalNotFoundException("Stock not found with id: " + id, null));
    }

    @Override
    public List<Stocks> getAllStocks() {
        List<Stocks> stocks = stocksRepo.findAll();
        if (stocks.isEmpty()) {
            throw new GlobalNotFoundException("No stocks found", null);
        }
        return stocks;
    }

    @Override
    public List<StockDTO> getAllStocksDTO() {
        List<Stocks> stocks = getAllStocks();
        return stocks.stream().map(this::StockToDTO).toList();
    }

    @Override
    public StockDTO createStockDTO(Long id) {
        Stocks stock = getStockById(id);
        StockExchangeSummaryDTO stockExchangeSummaryDTO = stockExchangeService.createStockExchangeSummaryDTO(stock.getStockExchange());

        stockPriceUpdater.updatePrice(stock);

        StockDTO stockDTO = new StockDTO();
        stockDTO.setStockID(stock.getId());
        stockDTO.setSymbol(stock.getSymbol());
        stockDTO.setCompanyName(stock.getCompanyName());
        stockDTO.setStockExchangeSummaryDTO(stockExchangeSummaryDTO);
        stockDTO.setCurrency(stock.getCurrency());
        stockDTO.setPrice(stock.getPrice());
        stockDTO.setAsk(stock.getAsk());
        stockDTO.setBid(stock.getBid());
        return stockDTO;
    }

    @Override
    public StockDTO StockToDTO(Stocks stocks) {
        StockExchangeSummaryDTO stockExchangeSummaryDTO = stockExchangeService.createStockExchangeSummaryDTO(stocks.getStockExchange());

        StockDTO stockDTO = new StockDTO();
        stockDTO.setStockID(stocks.getId());
        stockDTO.setSymbol(stocks.getSymbol());
        stockDTO.setCompanyName(stocks.getCompanyName());
        stockDTO.setStockExchangeSummaryDTO(stockExchangeSummaryDTO);
        stockDTO.setCurrency(stocks.getCurrency());
        stockDTO.setPrice(stocks.getPrice());
        stockDTO.setAsk(stocks.getAsk());
        stockDTO.setBid(stocks.getBid());
        return stockDTO;
    }
}
