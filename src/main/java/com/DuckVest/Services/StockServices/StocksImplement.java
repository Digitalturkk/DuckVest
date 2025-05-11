package com.DuckVest.Services.StockServices;

import com.DuckVest.Models.Stocks;
import com.DuckVest.Repositories.StocksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StocksImplement implements StockService {

    @Autowired
    StocksRepo stocksRepo;

    @Override
    public void saveStocks(Stocks stock) {
        stocksRepo.save(stock);
    }

    @Override
    public void deleteStocks(Long id) {
        stocksRepo.deleteById(id);
    }

    @Override
    public Stocks getStocks(Long id) {
        return stocksRepo.findById(id).get();
    }

    @Override
    public List<Stocks> getAllStocks() {
        return stocksRepo.findAll();
    }
}
