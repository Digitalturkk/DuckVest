package com.DuckVest.Services.InvestorServices;

import com.DuckVest.CustomEnums.AccountType;
import com.DuckVest.DTOs.InvestorAccountDTO;
import com.DuckVest.Models.Investor;

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
