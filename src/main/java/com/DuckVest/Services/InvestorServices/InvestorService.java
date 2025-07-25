package com.DuckVest.Services.InvestorServices;

import com.DuckVest.CustomEnums.AccountType;
import com.DuckVest.DTOs.InvestorDTOs.InvestorAccountDTO;
import com.DuckVest.DTOs.InvestorDTOs.InvestorCredentialsDTO;
import com.DuckVest.DTOs.InvestorDTOs.InvestorSummaryDTO;
import com.DuckVest.Models.Investor;

import java.util.List;

public interface InvestorService {

    // CRUD operations
    void saveInvestor(Investor investor);
    void deleteInvestor(Long id);
    Investor getInvestorById(Long id);
    List<Investor> getAllInvestors();

    // Account management
    void enableInvestorAccount(Long id);
    void disableInvestorAccount(Long id);
    void changeAccountType(Long id, AccountType type);
    void updateAge(Long id, Integer age);

    // Working with DTOs
    InvestorAccountDTO createInvestorDTO(Long id);
    InvestorSummaryDTO creatInvestorSummaryDTO(Long id);
    InvestorCredentialsDTO createInvestorCredentialsDTO(String username);
    List<InvestorAccountDTO> createAllInvestorAccountDTOs();
    Long checkInvestorCredentials(String username, String password);

    // Search functionality
    List<Investor> searchInvestorsByUsernameOrName(String username, String name);
 }
