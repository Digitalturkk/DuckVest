package com.DuckVest.Services.ExchangeServices;

import com.DuckVest.DTOs.StockExchangeSummaryDTO;
import com.DuckVest.Models.StockExchange;

import java.util.List;

public interface StockExchangeService {
     void saveExchange(StockExchange exchange);
     void deleteExchange(Long id);
     List<StockExchange> getAllExchange();

     StockExchangeSummaryDTO createStockExchangeSummaryDTO(StockExchange stockExchange);
}
