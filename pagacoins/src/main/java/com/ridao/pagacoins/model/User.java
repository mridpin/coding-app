package com.ridao.pagacoins.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class User {

    @Id
    private Long id;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Wallet> wallets;

}
