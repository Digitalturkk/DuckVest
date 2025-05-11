package com.DuckVest.Controllers;

import com.DuckVest.Models.StockExchange;
import com.DuckVest.Services.ExchangeServices.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exchanges")
public class ExchangeController {

    @Autowired
    ExchangeService exchangeService;

    @GetMapping("/all")
    public List<StockExchange> getExchange() {
        return exchangeService.getAllExchange();
    }

    @PostMapping("/add")
    public String addExchange(@RequestBody StockExchange stockExchange) {
        exchangeService.saveExchange(stockExchange);
        return "Exchange added successfully";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteExchange(@PathVariable Long id) {
        exchangeService.deleteExchange(id);
        return "Exchange deleted successfully";
    }
}
