package com.DuckVest.Controllers;

import com.DuckVest.Models.StockExchange;
import com.DuckVest.Services.ExchangeServices.StockExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exchanges")
public class ExchangeController {

    @Autowired
    StockExchangeService stockExchangeService;

    @GetMapping("/all")
    public List<StockExchange> getExchange() {
        return stockExchangeService.getAllExchange();
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
