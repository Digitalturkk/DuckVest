package backend.stocks.Service.InvestorServices;

import backend.stocks.CustomEnums.AccountType;
import backend.stocks.Models.Investors;
import backend.stocks.Repositories.InvestorsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InverstorImplement implements InvestorService {

    @Autowired
    InvestorsRepo investorsRepo;

    @Override
    public List<Investors> getAllInvestors() {
        return investorsRepo.findAll();
    }

    @Override
    public Investors getInvestor(Long id) {
        return investorsRepo.findById(id).get();
    }

    @Override
    public void saveInvestor(Investors investor) {
        investorsRepo.save(investor);
    }

    @Override
    public void deleteInvestor(Long id) {
        investorsRepo.deleteById(id);
    }

    @Override
    public void enableInvestorAccount(Long id) {
        Investors investor = investorsRepo.findById(id).get();
        investor.setInvestorAccountEnable(true);
    }

    @Override
    public void disableInvestorAccount(Long id) {
        Investors investor = investorsRepo.findById(id).get();
        investor.setInvestorAccountEnable(false);
    }

    @Override
    public void changeAccountType(Long id, AccountType accountType) {
        Investors investor = investorsRepo.findById(id).get();
        investor.setAccountType(accountType);
    }

    @Override
    public void updateAge(Long id, Integer age) {
        Investors investor =investorsRepo.findById(id).get();
        investor.setAge(age);
    }


}
