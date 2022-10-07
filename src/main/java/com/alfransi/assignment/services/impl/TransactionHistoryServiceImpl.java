package com.alfransi.assignment.services.impl;

import com.alfransi.assignment.converter.Converter;
import com.alfransi.assignment.dto.TransactionHistoryDto;
import com.alfransi.assignment.models.TransactionHistory;
import com.alfransi.assignment.repositories.TransactionHistoryRepository;
import com.alfransi.assignment.services.TransactionHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

    private final TransactionHistoryRepository transactionHistoryRepository;
    private final Converter converter;

    @Override
    public List<TransactionHistoryDto> getTransactionHistory(String accountNumber) {
        return transactionHistoryRepository.findByAccountNumberFrom(accountNumber)
                .stream().map(converter::toTransactionHistoryDto).collect(Collectors.toList());
    }
}
