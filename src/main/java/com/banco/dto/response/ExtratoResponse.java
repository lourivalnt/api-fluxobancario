package com.banco.dto.response;

import lombok.*;

import java.util.List;

/**
 * DTO de resposta para o extrato bancário.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExtratoResponse {

    private List<TransacaoResponse> transacoes;
}

