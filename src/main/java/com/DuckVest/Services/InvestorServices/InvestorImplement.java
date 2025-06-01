package com.DuckVest.Services.InvestorServices;

import com.DuckVest.CustomEnums.AccountType;
import com.DuckVest.DTOs.InvestorAccountDTO;
import com.DuckVest.DTOs.InvestorSummaryDTO;
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
        investor.setIsInvestorAccountEnable(true);
    }

    @Override
    public void disableInvestorAccount(Long id) {
        Investor investor = investorsRepo.findById(id).get();
        investor.setIsInvestorAccountEnable(false);
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

    @Override
    public InvestorAccountDTO createInvestorDTO(Long id, int portfolioId) {
        Investor investor = investorsRepo.findById(id).get();
        Portfolio portfolio = investor.getPortfolios().get(portfolioId - 1); // Assuming the investor has at least one portfolio and portfolioId is 1-based index

        InvestorAccountDTO investorAccountDTO = new InvestorAccountDTO();
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
        Investor investor = investorsRepo.findById(id).get();
        return new InvestorSummaryDTO(investor.getName(), investor.getEmail(), investor.getPhone());
    }

}
