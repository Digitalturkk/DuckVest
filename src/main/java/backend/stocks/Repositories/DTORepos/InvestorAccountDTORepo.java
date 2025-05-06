package backend.stocks.Repositories.DTORepos;

import backend.stocks.DTO.InvestorAccountDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestorAccountDTORepo extends JpaRepository<InvestorAccountDTO, Long> {

}
