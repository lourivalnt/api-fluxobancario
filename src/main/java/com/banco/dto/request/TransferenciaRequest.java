package com.banco.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO para transferência entre contas.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferenciaRequest {

    @NotNull(message = "Conta de origem é obrigatória")
    private UUID contaOrigemId;

    @NotNull(message = "Conta de destino é obrigatória")
    private UUID contaDestinoId;

    @NotNull(message = "O valor é obrigatório")
    @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero")
    private BigDecimal valor;
}

