package com.DuckVest.Services.ExchangeServices;

import com.DuckVest.DTOs.StockExchangeDTOs.StockExchangeDTO;
import com.DuckVest.DTOs.StockExchangeDTOs.StockExchangeSummaryDTO;
import com.DuckVest.Models.StockExchange;

import java.util.List;

public interface StockExchangeService {
     StockExchange getExchangeById(Long id);
     List<StockExchangeDTO> getAllExchangeDTOs();
     void saveExchange(StockExchange exchange);
     void deleteExchange(Long id);

     StockExchangeSummaryDTO createStockExchangeSummaryDTO(StockExchange stockExchange);
     StockExchangeDTO createStockExchangeDTO(Long id);
}
