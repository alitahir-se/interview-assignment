package com.alfransi.assignment.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import org.springframework.lang.NonNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name="ACCOUNTS")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @SequenceGenerator(
            name = "account_id_sequence",
            sequenceName = "account_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_id_sequence"
    )
    @Column(name = "ID")
    private Long id;

    @NonNull
    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @NonNull
    @Column(name = "AVAILABLE_BALANCE")
    private BigDecimal availableBalance;

    @NonNull
    @Column(name = "CURRENT_BALANCE")
    private BigDecimal currentBalance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Account account = (Account) o;
        return id != null && Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
