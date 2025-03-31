package backend.stocks.Repositories;

import backend.stocks.Models.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StocksRepo  extends JpaRepository<Stocks, Long> {
}

