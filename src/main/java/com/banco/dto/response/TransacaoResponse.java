package com.banco.dto.response;

import com.banco.domain.enums.TipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO que representa um item do extrato bancário.
 */
public record TransacaoResponse(
        UUID id,
        TipoTransacao tipo,
        BigDecimal valor,
        LocalDateTime dataHora
) {}

