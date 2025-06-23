package com.DuckVest.Services.ExchangeServices;

import com.DuckVest.DTOs.StockExchangeDTOs.StockExchangeDTO;
import com.DuckVest.DTOs.StockExchangeDTOs.StockExchangeSummaryDTO;
import com.DuckVest.Exceptions.GlobalNotFound.GlobalNotFoundException;
import com.DuckVest.Models.StockExchange;
import com.DuckVest.Repositories.StockExchangeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockExchangeImplement implements StockExchangeService {

    @Autowired
    StockExchangeRepo stockExchangeRepo;

    @Override
    public StockExchange getExchangeById(Long id) {
        return stockExchangeRepo.findById(id).orElseThrow(() -> new GlobalNotFoundException("Stock Exchange not found with id: " + id, null));
    }

    @Override
    public List<StockExchangeDTO> getAllExchangeDTOs() {
        List<StockExchangeDTO> stockExchangeDTOList = new ArrayList<>();
        List<StockExchange> stockExchanges = stockExchangeRepo.findAll();

        if (stockExchanges.isEmpty()) {
            throw new GlobalNotFoundException("No Stock Exchanges found", null);
        }

        for (StockExchange stockExchange : stockExchanges) {
            stockExchangeDTOList.add(createStockExchangeDTO(stockExchange.getId()));
        }

        return stockExchangeDTOList;
    }

    @Override
    public void saveExchange(StockExchange stockExchange) {
        stockExchangeRepo.save(stockExchange);
    }

    @Override
    public void deleteExchange(Long id) {
        if (!stockExchangeRepo.existsById(id)) {
            throw new GlobalNotFoundException("Stock Exchange not found with id: " + id, null);
        }
        stockExchangeRepo.deleteById(id);
    }

    @Override
    public StockExchangeSummaryDTO createStockExchangeSummaryDTO(StockExchange stockExchange) {
        StockExchangeSummaryDTO exchangeSummary = new StockExchangeSummaryDTO();
        exchangeSummary.setId(stockExchange.getId());
        exchangeSummary.setName(stockExchange.getName());
        exchangeSummary.setCountry(stockExchange.getCountry());
        return exchangeSummary;
    }

    @Override
    public StockExchangeDTO createStockExchangeDTO(Long id) {
        StockExchange stockExchange = stockExchangeRepo.findById(id).orElseThrow(() -> new GlobalNotFoundException("Stock Exchange not found with id: " + id, null));

        StockExchangeDTO stockExchangeDTO = new StockExchangeDTO();
        stockExchangeDTO.setId(stockExchange.getId());
        stockExchangeDTO.setName(stockExchange.getName());
        stockExchangeDTO.setCountry(stockExchange.getCountry());
        stockExchangeDTO.setCity(stockExchange.getCity());
        stockExchangeDTO.setCurrency(stockExchange.getCurrency());
        stockExchangeDTO.setIsActive(stockExchange.getIsActive());
        stockExchangeDTO.setOpenTime(stockExchange.getOpenTime());
        stockExchangeDTO.setCloseTime(stockExchange.getCloseTime());

        return stockExchangeDTO;
    }

}