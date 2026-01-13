package com.banco.mapper;

import com.banco.domain.entity.Transacao;
import com.banco.dto.response.ExtratoResponse;
import com.banco.dto.response.TransacaoResponse;

import java.util.List;

/**
 * Mapper responsável por montar o extrato bancário.
 */
public final class ExtratoMapper {

    private ExtratoMapper() {}

    /**
     * Converte lista de TransacaoEntity em ExtratoResponse.
     */
    public static ExtratoResponse toResponse(List<Transacao> transacoes) {

        List<TransacaoResponse> itens = transacoes.stream()
                .map(TransacaoMapper::toResponse)
                .toList();

        return new ExtratoResponse(itens);
    }
}
