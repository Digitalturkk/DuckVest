package com.DuckVest.Services.ExchangeServices;

import com.DuckVest.DTOs.StockExchangeDTO;
import com.DuckVest.DTOs.StockExchangeSummaryDTO;
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
        return stockExchangeRepo.findById(id).orElse(null);
    }

    @Override
    public List<StockExchangeDTO> getAllExchangeDTOs() {
        List<StockExchangeDTO> stockExchangeDTOList = new ArrayList<>();

        for (StockExchange stockExchange : stockExchangeRepo.findAll()) {
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
        StockExchange stockExchange = stockExchangeRepo.findById(id).orElse(null);
        if (stockExchange == null) {
            return null;
        }

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