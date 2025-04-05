package backend.stocks.Service.InvestorServices;

import backend.stocks.CustomEnums.AccountType;
import backend.stocks.Models.Investors;

import java.util.List;

public interface InvestorService {
    void saveInvestor(Investors investor);
    void deleteInvestor(Long id);
    Investors getInvestor(Long id);
    List<Investors> getAllInvestors();
    void enableInvestorAccount(Long id);
    void disableInvestorAccount(Long id);
    void changeAccountType(Long id, AccountType type);
    void updateAge(Long id, Integer age);
}
