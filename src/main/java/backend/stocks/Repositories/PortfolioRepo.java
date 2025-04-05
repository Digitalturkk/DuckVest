package backend.stocks.Repositories;

import backend.stocks.Models.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepo extends JpaRepository<Portfolio, Long> {
}
