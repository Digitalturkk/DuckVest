package com.DuckVest.Services.BankTransactionServices;

import com.DuckVest.DTOs.BankMoneyTransactionDTO;
import com.DuckVest.Models.BankTransaction;

import java.util.List;

public interface BankTransactionService {

    void saveBankTransaction(BankTransaction bankTransaction);
    BankTransaction getBankTransactionById(Long id);
    List<BankTransaction> getAllBankTransactions();
    void deleteBankTransaction(Long id);

    BankMoneyTransactionDTO transferBankTransactionToBankMoneyTransactionDTO(BankTransaction bankTransaction);
}
