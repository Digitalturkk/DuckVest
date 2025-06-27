package com.DuckVest.Controllers;

import com.DuckVest.DTOs.WatchListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.DuckVest.Services.WatchListServices.WatchListService;

@RestController
@RequestMapping("/watchlist")
@CrossOrigin(origins = "*")
public class WatchListController {

    @Autowired
    private WatchListService watchListService;

    @GetMapping("/get-wl={investorId}")
    public WatchListDTO getWatchListByInvestorId(@PathVariable Long investorId) {
        return watchListService.createWatchListDTO(investorId);
    }

    @PostMapping("/add-stock={stockId}-to-wl={watchListId}")
    public void addStockToWatchList(@PathVariable Long watchListId, @PathVariable Long stockId) {
        watchListService.addStockToWatchList(watchListId, stockId);
    }

    @PostMapping("/rmv-stock={stockId}-to-wl={watchListId}")
    public void removeStockFromWatchList(@PathVariable Long watchListId, @PathVariable Long stockId) {
        watchListService.removeStockFromWatchList(watchListId, stockId);
    }

}
