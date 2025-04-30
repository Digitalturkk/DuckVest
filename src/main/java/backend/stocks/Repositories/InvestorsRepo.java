package backend.stocks.Repositories;

import backend.stocks.Models.Investor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestorsRepo extends JpaRepository<Investor, Long> {
}
