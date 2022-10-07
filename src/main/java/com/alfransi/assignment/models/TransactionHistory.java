package com.alfransi.assignment.models;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "TRANSACTION_HISTORY")
@Entity
@Builder
public class TransactionHistory {

    @Id
    @SequenceGenerator(
            name = "transaction_history_id_sequence",
            sequenceName = "transaction_history_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_history_id_sequence"
    )
    private Long id;

    @NonNull
    @Column(name="ACCOUNT_NUMBER_TO")
    private String accountNumberTo;

    @NonNull
    @Column(name="ACCOUNT_NUMBER_FROM")
    private String accountNumberFrom;

    @NonNull
    @Column(name="AMOUNT")
    private BigDecimal amount;


    public TransactionHistory() {

    }
}
