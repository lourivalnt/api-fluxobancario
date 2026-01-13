package com.banco.dto.response;


import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO de resposta para dados básicos da conta.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContaResponse {

    private UUID id;
    private String titular;
    private BigDecimal saldo;
}

