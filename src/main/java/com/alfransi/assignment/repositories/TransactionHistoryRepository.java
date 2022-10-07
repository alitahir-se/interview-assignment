package com.alfransi.assignment.repositories;

import com.alfransi.assignment.models.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory,Long> {

    List<TransactionHistory> findByAccountNumberFrom(String accountNumber);
}
