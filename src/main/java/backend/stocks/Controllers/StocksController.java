package backend.stocks.Controllers;

import backend.stocks.Models.Stocks;
import backend.stocks.Service.StockServices.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StocksController {

    @Autowired
    StockService stockService;

    @GetMapping("/all")
    public List<Stocks> getAllStocks() {
        return stockService.getAllStocks();
    }

    @GetMapping("/{id}")
    public Stocks getStocks(@PathVariable Long id) {
        return stockService.getStocks(id);
    }

    @PostMapping("/add")
    public void addStocks(@RequestBody Stocks stocks) {
        stockService.saveStocks(stocks);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStocks(@PathVariable Long id) {
        stockService.deleteStocks(id);
    }
}
