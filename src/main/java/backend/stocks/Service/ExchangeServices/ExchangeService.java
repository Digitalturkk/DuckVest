package backend.stocks.Service.ExchangeServices;

import backend.stocks.Models.StockExchange;

import java.util.List;

public interface ExchangeService {
    public void saveExchange(StockExchange exchange);
    public void deleteExchange(Long id);
    public List<StockExchange> getAllExchange();
}
