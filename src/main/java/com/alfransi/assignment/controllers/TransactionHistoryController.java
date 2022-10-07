package com.alfransi.assignment.controllers;


import com.alfransi.assignment.dto.TransactionHistoryDto;
import com.alfransi.assignment.services.TransactionHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/transaction/history")
public class TransactionHistoryController {

    private final TransactionHistoryService transactionHistoryService;

    @GetMapping("/{accountNumber}")
   public ResponseEntity<List<TransactionHistoryDto>> getTransactionHistory(@PathVariable("accountNumber")String accountNumber){
       return ResponseEntity.ok(transactionHistoryService.getTransactionHistory(accountNumber));
   }
}
