package backend.stocks.Service.DTO.InvestorAccountDTO;

import backend.stocks.DTO.InvestorAccountDTO;

import java.util.List;

public interface InvestorAccountDTOService {
    List<InvestorAccountDTO> getAllInvestorAccounts();
    InvestorAccountDTO getInvestorAccountById(Long id);
    void saveInvestorAccount(InvestorAccountDTO investorAccountDTO);
    void deleteInvestorAccount(Long id);
}
