package com.banco.mapper;

import com.banco.domain.entity.Conta;
import com.banco.dto.response.ContaResponse;

/**
 * Mapper responsável por converter ContaEntity em DTOs.
 */
public final class ContaMapper {

    private ContaMapper() {}

    /**
     * Converte uma entidade Conta para ContaResponse.
     */
    public static ContaResponse toResponse(Conta entity) {
        if (entity == null) {
            return null;
        }

        return new ContaResponse(
                entity.getId(),
                entity.getTitular(),
                entity.getSaldo()
        );
    }
}

