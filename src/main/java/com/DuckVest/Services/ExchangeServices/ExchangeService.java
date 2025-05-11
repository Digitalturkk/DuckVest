package com.DuckVest.Services.ExchangeServices;

import com.DuckVest.Models.StockExchange;

import java.util.List;

public interface ExchangeService {
    public void saveExchange(StockExchange exchange);
    public void deleteExchange(Long id);
    public List<StockExchange> getAllExchange();
}
