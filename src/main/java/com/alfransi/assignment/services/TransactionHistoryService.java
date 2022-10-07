package com.alfransi.assignment.services;

import com.alfransi.assignment.dto.TransactionHistoryDto;

import java.util.List;

public interface TransactionHistoryService {

    List<TransactionHistoryDto> getTransactionHistory(String accountNumber);

}
