package com.banco.mapper;

import com.banco.domain.entity.Transacao;
import com.banco.dto.response.TransacaoResponse;

/**
 * Mapper responsável por converter TransacaoEntity em DTOs.
 */
public final class TransacaoMapper {

    private TransacaoMapper() {}

    /**
     * Converte uma entidade Transacao para TransacaoResponse.
     */
    public static TransacaoResponse toResponse(Transacao entity) {
        if (entity == null) {
            return null;
        }

        return new TransacaoResponse(
                entity.getId(),
                entity.getTipo(),
                entity.getValor(),
                entity.getDataHora()
        );
    }
}

