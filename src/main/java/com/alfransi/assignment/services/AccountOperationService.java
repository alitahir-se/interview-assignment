package com.alfransi.assignment.services;

import com.alfransi.assignment.dto.AccountInfoDto;
import com.alfransi.assignment.dto.TransferRequestDto;
import com.alfransi.assignment.exception.ResourceNotFoundException;

public interface AccountOperationService {
    String createTransfer(TransferRequestDto transferDto);
    AccountInfoDto getAccountInfo(final String accountNumber) throws ResourceNotFoundException;
}
