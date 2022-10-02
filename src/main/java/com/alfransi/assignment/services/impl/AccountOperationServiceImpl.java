package com.alfransi.assignment.services.impl;

import com.alfransi.assignment.common.AppConstants;
import com.alfransi.assignment.dto.AccountInfoDto;
import com.alfransi.assignment.dto.TransferRequestDto;
import com.alfransi.assignment.exception.BusinessException;
import com.alfransi.assignment.exception.ResourceNotFoundException;
import com.alfransi.assignment.models.Account;
import com.alfransi.assignment.repositories.AccountsRepository;
import com.alfransi.assignment.services.AccountOperationService;
import com.alfransi.assignment.converter.Converter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountOperationServiceImpl implements AccountOperationService {

    private final AccountsRepository accountsRepository;
    private final Converter converter;

    @Override
    @Transactional
    public String createTransfer(TransferRequestDto transferDto) {

        Optional<Account> senderAccount = accountsRepository.fetchByAccountNumberAndObtainLock(transferDto.getAccountFrom());
        Optional<Account> receiverAccount = accountsRepository.fetchByAccountNumberAndObtainLock(transferDto.getAccountTo());
        if(senderAccount.isEmpty() || receiverAccount.isEmpty()){
            throw new ResourceNotFoundException(AppConstants.TransactionErrors.ACCOUNT_NOT_FOUND.value());
        }

        Account debitAccount = senderAccount.get();
        Account creditAccount = receiverAccount.get();

        if(debitAccount.getAvailableBalance().compareTo(transferDto.getAmount())<0){
            throw new BusinessException(AppConstants.TransactionErrors.INSUFFICIENT_BALANCE.value());
        }

        debitAccount.setAvailableBalance(debitAccount.getAvailableBalance().subtract(transferDto.getAmount()));
        debitAccount.setCurrentBalance(debitAccount.getCurrentBalance().subtract(transferDto.getAmount()));

        creditAccount.setAvailableBalance(creditAccount.getAvailableBalance().add(transferDto.getAmount()));
        creditAccount.setCurrentBalance(creditAccount.getCurrentBalance().add(transferDto.getAmount()));

        accountsRepository.save(debitAccount);
        accountsRepository.save(creditAccount);

        return AppConstants.TRANSACTION_SUCCESS;
    }

    @Override
    public AccountInfoDto getAccountInfo(String accountNumber) {
         return accountsRepository.findByAccountNumber(accountNumber)
                 .map(converter::toAccountInfoDto)
                 .orElseThrow(()-> new ResourceNotFoundException(AppConstants.TransactionErrors.ACCOUNT_NOT_FOUND.value()));

    }
}
