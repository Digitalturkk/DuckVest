package com.DuckVest.Services.ExchangeServices;

import com.DuckVest.DTOs.StockExchangeSummaryDTO;
import com.DuckVest.Models.StockExchange;
import com.DuckVest.Repositories.ExchangeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockExchangeImplement implements StockExchangeService {

    @Autowired
    ExchangeRepo exchangeRepo;

    @Override
    public void saveExchange(StockExchange stockExchange) {
        exchangeRepo.save(stockExchange);
    }

    @Override
    public void deleteExchange(Long id) {
        exchangeRepo.deleteById(id);
    }

    @Override
    public List<StockExchange> getAllExchange() {
        return exchangeRepo.findAll();
    }

    @Override
    public StockExchangeSummaryDTO createStockExchangeSummaryDTO(StockExchange stockExchange) {
        StockExchangeSummaryDTO exchangeSummary = new StockExchangeSummaryDTO();
        exchangeSummary.setId(stockExchange.getId());
        exchangeSummary.setName(stockExchange.getName());
        exchangeSummary.setCountry(stockExchange.getCountry());
        return exchangeSummary;
    }

}