package com.alfransi.assignment.converter;


import com.alfransi.assignment.dto.AccountInfoDto;
import com.alfransi.assignment.dto.TransactionHistoryDto;
import com.alfransi.assignment.dto.TransferRequestDto;
import com.alfransi.assignment.models.Account;
import com.alfransi.assignment.models.TransactionHistory;
import org.springframework.stereotype.Component;

@Component
public class Converter {

    public AccountInfoDto toAccountInfoDto(Account account){
        return AccountInfoDto.builder()
                .accountNumber(account.getAccountNumber())
                .availableBalance(account.getAvailableBalance()).currentBalance(account.getCurrentBalance())
                .build();

    }

    public TransactionHistoryDto toTransactionHistoryDto(TransactionHistory transactionHistory){
        return TransactionHistoryDto.builder()
                .accountNumberFrom(transactionHistory.getAccountNumberFrom()).accountNumberTo(transactionHistory.getAccountNumberTo())
                .amount(transactionHistory.getAmount()).id(transactionHistory.getId()).build();

    }

    public TransactionHistory transactionHistory(TransferRequestDto transferRequestDto){
        return TransactionHistory.builder().accountNumberFrom(transferRequestDto.getAccountFrom())
                .accountNumberTo(transferRequestDto.getAccountTo()).amount(transferRequestDto.getAmount())
                .build();
    }
}
