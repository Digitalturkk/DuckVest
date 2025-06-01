package com.DuckVest.Services.InvestorServices;

import com.DuckVest.CustomEnums.AccountType;
import com.DuckVest.DTOs.InvestorAccountDTO;
import com.DuckVest.DTOs.InvestorSummaryDTO;
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

    InvestorAccountDTO createInvestorDTO(Long id, int portfolioId); // We use int here because we have list of portfolios, so we need to get it by its index
    InvestorSummaryDTO creatInvestorSummaryDTO(Long id);
 }
