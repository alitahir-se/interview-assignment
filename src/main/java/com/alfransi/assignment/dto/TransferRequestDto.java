package com.alfransi.assignment.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequestDto implements Serializable {

    @NotNull(message = "account information field cannot be empty")
    @Size(min=15,max = 15,message = "Invalid Account Number")
    String accountFrom;

    @NotNull(message = "account information field cannot be empty")
    @Size(min=15,max = 15,message = "Invalid Account Number")
    String accountTo;

    @NotNull(message = "amount field cannot be empty")
    @Min(value = 1,message = "Minimum amount must be 1")
    BigDecimal amount;


}
