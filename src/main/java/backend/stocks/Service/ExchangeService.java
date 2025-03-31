package backend.stocks.Service;

import backend.stocks.Models.StockExchange;

import java.util.List;

public interface ExchangeService {
    public void addExchange(StockExchange exchange);
    public void deleteExchange(Long id);
    public List<StockExchange> getAllExchange();
}
