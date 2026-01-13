package com.banco.dto.response;

import lombok.*;

import java.util.List;

/**
 * DTO de resposta para o extrato bancário.
 */
public record ExtratoResponse(
        List<TransacaoResponse> transacoes
) {}
// Tudo implícito: private final fields
// Tudo implícito: getters (contaOrigemId(), contaDestinoId(), valor())
// Tudo implícito: equals(), hashCode(), toString()
// Tudo implícito: construtor com todos parâmetros
