package com.banco.mapper;

import com.banco.domain.entity.Transacao;
import com.banco.dto.response.TransacaoResponse;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper responsável por converter Transacao em DTOs.
 */
@Mapper(componentModel = "spring")
public interface TransacaoMapper {

    /**
     * Converte uma entidade Transacao para TransacaoResponse.
     */
    TransacaoResponse toResponse(Transacao entity);

    /**
     * Converte uma lista de Transacao para lista de TransacaoResponse.
     */
    List<TransacaoResponse> toResponseList(List<Transacao> entities);
}


