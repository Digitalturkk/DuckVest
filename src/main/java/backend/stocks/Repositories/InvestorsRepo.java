package backend.stocks.Repositories;

import backend.stocks.Models.Investors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestorsRepo extends JpaRepository<Investors, Long> {
}
