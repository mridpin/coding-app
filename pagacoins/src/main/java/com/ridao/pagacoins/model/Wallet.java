package com.ridao.pagacoins.model;

import lombok.*;

import javax.persistence.*;

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
    @JoinColumn(name = "userId")
    private User user;

}
