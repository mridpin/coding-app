package com.ridao.pagacoins.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Wallet {

    @Id
    private Long id;

    private Double balance;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}
