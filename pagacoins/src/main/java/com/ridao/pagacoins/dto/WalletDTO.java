package com.ridao.pagacoins.dto;

import com.ridao.pagacoins.model.Transaction;
import com.ridao.pagacoins.model.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class WalletDTO {

    private Long id;

    private Double balance;

    private UserDTO user;

    private List<Transaction> transactions;
}