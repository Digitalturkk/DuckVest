package backend.stocks.Services.InvestorServices;

import backend.stocks.CustomEnums.AccountType;
import backend.stocks.DTOs.InvestorAccountDTO;
import backend.stocks.Models.Investor;

import java.util.List;

public interface InvestorService {
    void saveInvestor(Investor investor);
    void deleteInvestor(Long id);
    Investor getInvestor(Long id);
    List<Investor> getAllInvestors();
    void enableInvestorAccount(Long id);
    void disableInvestorAccount(Long id);
    void changeAccountType(Long id, AccountType type);
    void updateAge(Long id, Integer age);

    InvestorAccountDTO getInvestorAccountInformation(Long id, int portfolioId);
}
