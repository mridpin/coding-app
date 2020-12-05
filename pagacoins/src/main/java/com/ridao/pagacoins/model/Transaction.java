package com.ridao.pagacoins.model;

import javax.persistence.*;

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

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "sender")
//    private Wallet sender;
//
//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "beneficiary")
//    private Wallet beneficiary;

}
