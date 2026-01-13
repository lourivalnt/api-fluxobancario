package com.banco.dto.response;

import com.banco.domain.enums.TipoTransacao;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO que representa um item do extrato bancário.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransacaoResponse {

    private UUID id;
    private TipoTransacao tipo;
    private BigDecimal valor;
    private LocalDateTime dataHora;
}

