package com.ridao.pagacoins.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Transaction {

    @Id
    private Long id;

    private Double amount;

    @OneToOne(fetch = FetchType.EAGER)
    private Wallet sender;

    @OneToOne(fetch = FetchType.EAGER)
    private Wallet beneficiary;

}
