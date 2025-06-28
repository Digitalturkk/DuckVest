package com.DuckVest.Services.StockServices;

import com.DuckVest.DTOs.StockDTO;
import com.DuckVest.Models.Stocks;

import java.util.List;

public interface StocksService {
     void saveStocks(Stocks stocks);
     void deleteStocks(Long id);
     Stocks getStockById(Long id);
     List<Stocks> getAllStocks();
     StockDTO createStockDTO(Long id);
     StockDTO StockToDTO(Stocks stocks);
     List<StockDTO> getAllStocksDTO();
}
