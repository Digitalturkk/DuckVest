package com.DuckVest.Services.InvestorServices;

import com.DuckVest.CustomEnums.AccountType;
import com.DuckVest.DTOs.InvestorDTOs.InvestorAccountDTO;
import com.DuckVest.DTOs.InvestorDTOs.InvestorSummaryDTO;
import com.DuckVest.Models.Investor;

import java.util.List;

public interface InvestorService {
    void saveInvestor(Investor investor);
    void deleteInvestor(Long id);

    Investor getInvestorById(Long id);
    List<Investor> getAllInvestors();
    void enableInvestorAccount(Long id);
    void disableInvestorAccount(Long id);
    void changeAccountType(Long id, AccountType type);
    void updateAge(Long id, Integer age);

    InvestorAccountDTO createInvestorDTO(Long id);
    InvestorSummaryDTO creatInvestorSummaryDTO(Long id);
    List<InvestorAccountDTO> createAllInvestorAccountDTOs();
 }
