package com.alfransi.assignment.converter;


import com.alfransi.assignment.dto.AccountInfoDto;
import com.alfransi.assignment.models.Account;
import org.springframework.stereotype.Component;

@Component
public class Converter {

    public AccountInfoDto toAccountInfoDto(Account account){
        return AccountInfoDto.builder()
                .accountNumber(account.getAccountNumber())
                .availableBalance(account.getAvailableBalance()).currentBalance(account.getCurrentBalance())
                .build();

    }
}
