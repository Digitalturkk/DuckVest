package backend.stocks.Service.DTO.InvestorAccountDTO;

import backend.stocks.DTO.InvestorAccountDTO;
import backend.stocks.Repositories.DTORepos.InvestorAccountDTORepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestorAccountDTOImplement implements InvestorAccountDTOService {

    @Autowired
    private InvestorAccountDTORepo investorAccountDTORepo;

    @Override
    public List<InvestorAccountDTO> getAllInvestorAccounts() {
        return investorAccountDTORepo.findAll();
    }

    @Override
    public InvestorAccountDTO getInvestorAccountById(Long id) {
        return investorAccountDTORepo.findById(id).get();
    }

    @Override
    public void saveInvestorAccount(InvestorAccountDTO investorAccountDTO) {
       investorAccountDTORepo.save(investorAccountDTO);
    }

    @Override
    public void deleteInvestorAccount(Long id) {
        investorAccountDTORepo.deleteById(id);
    }
}
