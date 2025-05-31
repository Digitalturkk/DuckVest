package com.DuckVest.Controllers;

import com.DuckVest.DTOs.StockExchangeDTO;
import com.DuckVest.Models.StockExchange;
import com.DuckVest.Services.ExchangeServices.StockExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exchanges")
public class StockExchangeController {

    @Autowired
    StockExchangeService stockExchangeService;

    @GetMapping("/get-dto-{id}")
    public StockExchangeDTO getExchangeById(@PathVariable Long id) {
        return stockExchangeService.createStockExchangeDTO(id);
    }

    @GetMapping("/all-dto")
    public List<StockExchangeDTO> getExchange() {
        return stockExchangeService.getAllExchangeDTOs();
    }

    @PostMapping("/add")
    public String addExchange(@RequestBody StockExchange stockExchange) {
        stockExchangeService.saveExchange(stockExchange);
        return "Exchange added successfully";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteExchange(@PathVariable Long id) {
        stockExchangeService.deleteExchange(id);
        return "Exchange deleted successfully";
    }
}
