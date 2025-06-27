package com.DuckVest.Services.WatchListServices;

import com.DuckVest.DTOs.WatchListDTO;
import com.DuckVest.Models.WatchList;

public interface WatchListService {
    WatchList getWatchListByInvestorId(Long investorId);
    void updateWatchList(WatchList watchList);
    void saveWatchList(WatchList watchList);
    void deleteWatchList(Long watchListId);

    WatchListDTO createWatchListDTO(Long watchListId);
    void addStockToWatchList(Long watchListId, Long stockId);
    void removeStockFromWatchList(Long watchListId, Long stockId);
}
