package com.DuckVest.Services.StockServices;

import com.DuckVest.Models.Stocks;

import java.util.List;

public interface StockService {
     void saveStocks(Stocks stocks);
     void deleteStocks(Long id);
     Stocks getStocks(Long id);
     List<Stocks> getAllStocks();
}
