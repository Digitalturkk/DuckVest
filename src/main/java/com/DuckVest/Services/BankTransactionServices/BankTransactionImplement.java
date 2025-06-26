package com.DuckVest.Services.BankTransactionServices;

import com.DuckVest.DTOs.BankMoneyTransactionDTO;
import com.DuckVest.Exceptions.GlobalNotFound.GlobalNotFoundException;
import com.DuckVest.Models.BankTransaction;
import com.DuckVest.Repositories.BankTransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankTransactionImplement implements BankTransactionService {

    @Autowired
    private BankTransactionRepo bankTransactionRepo;

    @Override
    public void saveBankTransaction(BankTransaction bankTransaction) {
        bankTransactionRepo.save(bankTransaction);
    }

    @Override
    public BankTransaction getBankTransactionById(Long id) {
       return bankTransactionRepo.findById(id).orElseThrow(()-> new GlobalNotFoundException("Bank Transaction with ID " + id + " not found.", null));
    }

    @Override
    public List<BankTransaction> getAllBankTransactions() {
        if (bankTransactionRepo.findAll().isEmpty()) {
            throw new GlobalNotFoundException("No bank transactions found.", null);
        }
        return bankTransactionRepo.findAll();
    }

    @Override
    public void deleteBankTransaction(Long id) {
        if (!bankTransactionRepo.existsById(id)) {
            throw new GlobalNotFoundException("Bank Transaction with ID " + id + " not found.", null);
        }
        bankTransactionRepo.deleteById(id);
    }

    @Override
    public BankMoneyTransactionDTO transferBankTransactionToBankMoneyTransactionDTO(BankTransaction bankTransaction) {
        if (bankTransaction == null) {
            throw new GlobalNotFoundException("Bank Transaction is null.", null);
        }
        return new BankMoneyTransactionDTO(
                bankTransaction.getAmount(),
                bankTransaction.getType(),
                bankTransaction.getCurrency(),
                bankTransaction.getCommission(),
                bankTransaction.getDescription()
        );
    }
}
