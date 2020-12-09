package com.ridao.pagacoins.dto;

import com.ridao.pagacoins.model.Wallet;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TransactionDTO {

    @NotNull
    private String senderHash;

    @NotNull
    private String beneficiaryHash;

    @NotNull
    private Double amount;

}