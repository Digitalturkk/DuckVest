package backend.stocks.Service.StockServices;

import backend.stocks.Models.Stocks;

import java.util.List;

public interface StockService {
     void saveStocks(Stocks stocks);
     void deleteStocks(Long id);
     Stocks getStocks(Long id);
     List<Stocks> getAllStocks();
}
