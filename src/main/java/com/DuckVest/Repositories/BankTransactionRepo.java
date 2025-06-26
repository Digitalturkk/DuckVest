package com.DuckVest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.DuckVest.Models.BankTransaction;

public interface BankTransactionRepo extends JpaRepository<BankTransaction, Long> {
}
