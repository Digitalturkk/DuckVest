package com.DuckVest.Services.WatchListServices;

import com.DuckVest.DTOs.StockDTO;
import com.DuckVest.DTOs.WatchListDTO;
import com.DuckVest.Exceptions.GlobalNotFound.GlobalNotFoundException;
import com.DuckVest.Models.Stocks;
import com.DuckVest.Models.WatchList;
import com.DuckVest.Repositories.WatchListRepo;
import com.DuckVest.Services.StockServices.StocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class WatchListImplement implements WatchListService {

    @Autowired
    private WatchListRepo watchListRepo;
    @Autowired
    private StocksService stocksService;

    @Override
    public WatchList getWatchListByInvestorId(Long watchListId) {
        return watchListRepo.findById(watchListId)
                .orElseThrow(() -> new GlobalNotFoundException("WatchList not found with id: " + watchListId, null));
    }

    @Override
    public void updateWatchList(WatchList watchList) {
        if (watchList == null || watchList.getId() == null) {
            throw new IllegalArgumentException("WatchList or WatchList ID cannot be null");
        }
        watchListRepo.save(watchList);
    }

    @Override
    public void saveWatchList(WatchList watchList) {
        if (watchList == null) {
            throw new IllegalArgumentException("WatchList cannot be null");
        }
        watchListRepo.save(watchList);
    }

    @Override
    public void deleteWatchList(Long watchListId) {
        if (!watchListRepo.existsById(watchListId)) {
            throw new GlobalNotFoundException("WatchList not found with id: " + watchListId, null);
        }
        watchListRepo.deleteById(watchListId);
    }

    @Override
    public WatchListDTO createWatchListDTO(Long watchListId) {
        WatchList watchList = getWatchListByInvestorId(watchListId);
        Set<Stocks> stocks = watchList.getStocks();
        Set<StockDTO> stocksSet = new HashSet<>();
        for (Stocks stock : stocks) {
            StockDTO stockDTO = stocksService.createStockDTO(stock.getId());
            stocksSet.add(stockDTO);
        }
        WatchListDTO watchListDTO = new WatchListDTO();
        watchListDTO.setStocks(stocksSet);
        return watchListDTO;
    }

    @Override
    public void addStockToWatchList(Long watchListId, Long stockId) {
        WatchList watchList = getWatchListByInvestorId(watchListId);
        Stocks stock = stocksService.getStockById(stockId);

        if (watchList.getStocks() == null) {
            watchList.setStocks(new HashSet<>());
        }

        watchList.getStocks().add(stock);
        updateWatchList(watchList);
    }
}
