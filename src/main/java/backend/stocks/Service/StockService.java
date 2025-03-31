package backend.stocks.Service;

import backend.stocks.Models.Stocks;

import java.util.List;

public interface StockService {
     void addStocks(Stocks stocks);
     void deleteStocks(Long id);
     Stocks getStocks(Long id);
     List<Stocks> getAllStocks();
}
