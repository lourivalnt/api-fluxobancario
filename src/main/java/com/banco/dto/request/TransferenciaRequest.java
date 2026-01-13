package com.banco.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO para transferência entre contas.
 */
public record TransferenciaRequest(

        @NotNull(message = "Conta de origem é obrigatória")
        UUID contaOrigemId,

        @NotNull(message = "Conta de destino é obrigatória")
        UUID contaDestinoId,

        @NotNull(message = "O valor é obrigatório")
        @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero")
        BigDecimal valor
) {}

