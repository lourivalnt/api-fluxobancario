package com.banco.mapper;

import com.banco.domain.entity.Transacao;
import com.banco.dto.response.ExtratoResponse;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Mapper responsável por montar o extrato bancário.
 *
 * Usa MapStruct para mapear as transações
 * e método default para compor o DTO agregado.
 */
@Mapper(componentModel = "spring")
public abstract class ExtratoMapper {

    @Autowired
    protected TransacaoMapper transacaoMapper;

    /**
     * Converte lista de Transacao em ExtratoResponse.
     */
    public ExtratoResponse toResponse(List<Transacao> transacoes) {
        return new ExtratoResponse(
                transacaoMapper.toResponseList(transacoes)
        );
    }
}
