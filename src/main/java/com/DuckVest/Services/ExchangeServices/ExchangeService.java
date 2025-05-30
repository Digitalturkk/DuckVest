package com.DuckVest.Services.ExchangeServices;

import com.DuckVest.Models.StockExchange;

import java.util.List;

public interface ExchangeService {
     void saveExchange(StockExchange exchange);
     void deleteExchange(Long id);
     List<StockExchange> getAllExchange();
}
