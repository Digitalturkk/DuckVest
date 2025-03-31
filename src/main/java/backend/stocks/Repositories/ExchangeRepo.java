package backend.stocks.Repositories;

import backend.stocks.Models.StockExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepo  extends JpaRepository<StockExchange, Long> {
}
