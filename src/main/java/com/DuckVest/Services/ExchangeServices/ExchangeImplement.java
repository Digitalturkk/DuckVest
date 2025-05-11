package com.DuckVest.Services.ExchangeServices;

import com.DuckVest.Models.StockExchange;
import com.DuckVest.Repositories.ExchangeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeImplement implements ExchangeService {

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

}