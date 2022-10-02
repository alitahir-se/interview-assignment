package com.alfransi.assignment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class AccountInfoDto implements Serializable {

    private String accountNumber;
    private BigDecimal availableBalance;
    private BigDecimal currentBalance;
}
