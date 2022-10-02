package com.alfransi.assignment.controllers;

import com.alfransi.assignment.dto.AccountInfoDto;
import com.alfransi.assignment.dto.TransferRequestDto;
import com.alfransi.assignment.services.AccountOperationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/operation")
public class AccountOperations {

    private final AccountOperationService accountOperationService;

    @PostMapping("/send")
    public ResponseEntity<String> transferMoney(@Valid @RequestBody TransferRequestDto transferRequest){
        return  ResponseEntity.ok(accountOperationService.createTransfer(transferRequest));
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountInfoDto> getAccountInfo(@PathVariable("accountNumber") String accountNumber){
        return ResponseEntity.ok(accountOperationService.getAccountInfo(accountNumber));
    }
}
