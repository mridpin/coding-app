package com.ridao.pagacoins.dto;

import com.ridao.pagacoins.model.Wallet;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TransactionDTO {

    private Long senderId;

    private Long beneficiaryId;

    private Double amount;

}