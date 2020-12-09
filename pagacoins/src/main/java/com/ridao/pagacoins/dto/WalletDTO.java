package com.ridao.pagacoins.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class WalletDTO {

    private Long id;

    private String hash;

    private Double balance;

    private UserDTO user;

}