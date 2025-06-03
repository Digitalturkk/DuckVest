package com.DuckVest.Services.InvestorServices;

import com.DuckVest.CustomEnums.AccountType;
import com.DuckVest.DTOs.InvestorAccountDTO;
import com.DuckVest.DTOs.InvestorSummaryDTO;
import com.DuckVest.Exceptions.GlobalNotFound.GlobalNotFoundException;
import com.DuckVest.Models.Investor;
import com.DuckVest.Models.Portfolio;
import com.DuckVest.Repositories.InvestorsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestorImplement implements InvestorService {

    @Autowired
    InvestorsRepo investorsRepo;

    @Override
    public List<Investor> getAllInvestors() {
        List<Investor> investors = investorsRepo.findAll();

        if (investors.isEmpty()) {
            throw new RuntimeException("No investors found");
        }
        return investors;
    }

    @Override
    public Investor getInvestor(Long id) {
        return investorsRepo.findById(id).orElseThrow( () -> new GlobalNotFoundException("Investor not found with id: " + id, null));
    }

    @Override
    public void saveInvestor(Investor investor) {
        investorsRepo.save(investor);
    }

    @Override
    public void deleteInvestor(Long id) {
        if (!investorsRepo.existsById(id)) {
            throw new GlobalNotFoundException("Investor not found with id: " + id, null);
        }
        investorsRepo.deleteById(id);
    }

    @Override
    public void enableInvestorAccount(Long id) {
        Investor investor = investorsRepo.findById(id).orElseThrow( () -> new GlobalNotFoundException("Investor not found with id: " + id, null));
        investor.setIsInvestorAccountEnable(true);
    }

    @Override
    public void disableInvestorAccount(Long id) {
        Investor investor = investorsRepo.findById(id).orElseThrow( () -> new GlobalNotFoundException("Investor not found with id: " + id, null));
        investor.setIsInvestorAccountEnable(false);
    }

    @Override
    public void changeAccountType(Long id, AccountType accountType) {
        Investor investor = investorsRepo.findById(id).orElseThrow( () -> new GlobalNotFoundException("Investor not found with id: " + id, null));
        investor.setAccountType(accountType);
    }

    @Override
    public void updateAge(Long id, Integer age) {
        Investor investor =investorsRepo.findById(id).orElseThrow( () -> new GlobalNotFoundException("Investor not found with id: " + id, null));
        investor.setAge(age);
    }

    @Override
    public InvestorAccountDTO createInvestorDTO(Long id, int portfolioId) {
        Investor investor = investorsRepo.findById(id).orElseThrow( () -> new GlobalNotFoundException("Investor not found with id: " + id, null));
        Portfolio portfolio = investor.getPortfolios().get(portfolioId - 1); // Assuming the investor has at least one portfolio and portfolioId is 1-based index

        InvestorAccountDTO investorAccountDTO = new InvestorAccountDTO();
        investorAccountDTO.setUsername(investor.getUsername());
        investorAccountDTO.setName(investor.getName());
        investorAccountDTO.setEmail(investor.getEmail());
        investorAccountDTO.setPhone(investor.getPhone());
        investorAccountDTO.setAccountType(investor.getAccountType());
        investorAccountDTO.setPortfolioBalance(portfolio.getTotalBalance());
        investorAccountDTO.setBuyingPower(portfolio.getAvailableBalance());
        investorAccountDTO.setIsAccountActive(investor.getIsInvestorAccountEnable());
        return investorAccountDTO;
    }

    @Override
    public InvestorSummaryDTO creatInvestorSummaryDTO(Long id) {
        Investor investor = investorsRepo.findById(id).orElseThrow( () -> new GlobalNotFoundException("Investor not found with id: " + id, null));
        return new InvestorSummaryDTO(investor.getName(), investor.getEmail(), investor.getPhone());
    }

}
