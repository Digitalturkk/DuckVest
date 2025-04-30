package backend.stocks.Service.InvestorServices;

import backend.stocks.CustomEnums.AccountType;
import backend.stocks.Models.Investor;
import backend.stocks.Repositories.InvestorsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InverstorImplement implements InvestorService {

    @Autowired
    InvestorsRepo investorsRepo;

    @Override
    public List<Investor> getAllInvestors() {
        return investorsRepo.findAll();
    }

    @Override
    public Investor getInvestor(Long id) {
        return investorsRepo.findById(id).get();
    }

    @Override
    public void saveInvestor(Investor investor) {
        investorsRepo.save(investor);
    }

    @Override
    public void deleteInvestor(Long id) {
        investorsRepo.deleteById(id);
    }

    @Override
    public void enableInvestorAccount(Long id) {
        Investor investor = investorsRepo.findById(id).get();
        investor.setInvestorAccountEnable(true);
    }

    @Override
    public void disableInvestorAccount(Long id) {
        Investor investor = investorsRepo.findById(id).get();
        investor.setInvestorAccountEnable(false);
    }

    @Override
    public void changeAccountType(Long id, AccountType accountType) {
        Investor investor = investorsRepo.findById(id).get();
        investor.setAccountType(accountType);
    }

    @Override
    public void updateAge(Long id, Integer age) {
        Investor investor =investorsRepo.findById(id).get();
        investor.setAge(age);
    }


}
