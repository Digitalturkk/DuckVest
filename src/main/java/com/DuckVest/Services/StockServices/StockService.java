package com.DuckVest.Services.StockServices;

import com.DuckVest.DTOs.OrderDTO;
import com.DuckVest.DTOs.StockDTO;
import com.DuckVest.Models.Stocks;

import java.util.List;

public interface StockService {
     void saveStocks(Stocks stocks);
     void deleteStocks(Long id);
     Stocks getStockById(Long id);
     List<Stocks> getAllStocks();
     StockDTO getStockDTO(Long id);

     OrderDTO buyStock(Long portfolioId, Long stockId, Double quantity, Double brokerFee);
}
