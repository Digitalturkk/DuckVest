package com.DuckVest.Services.StockServices;

import com.DuckVest.DTOs.StockDTO;
import com.DuckVest.Models.Stocks;

import java.util.List;

public interface StockService {
     void saveStocks(Stocks stocks);
     void deleteStocks(Long id);
     Stocks getStocks(Long id);
     List<Stocks> getAllStocks();
     StockDTO getStockDTO(Long id);

     String buyStock(Long portfolioId, Long stockId, int quantity);
}
