package com.alfransi.assignment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;


@Builder
@Getter
@Setter
public class TransactionHistoryDto implements Serializable {

    private Long id;
    private String accountNumberTo;
    private String accountNumberFrom;
    private BigDecimal amount;


}
