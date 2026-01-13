package com.banco.dto.response;


import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO de resposta para dados básicos da conta.
 */
public record ContaResponse(
        UUID id,
        String titular,
        BigDecimal saldo
) {}

