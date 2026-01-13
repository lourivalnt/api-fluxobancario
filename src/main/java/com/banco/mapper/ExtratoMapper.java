package com.banco.mapper;

import com.banco.domain.entity.Transacao;
import com.banco.dto.response.ExtratoResponse;
import com.banco.dto.response.TransacaoResponse;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper responsável por montar o extrato bancário.
 * "Usei o MapStruct pra converter cada transação individual.
 * Depois, com método default, juntei todas no formato de extrato
 * que a tela precisa."
 */
@Mapper(
        componentModel = "spring",
        uses = TransacaoMapper.class
)
public interface ExtratoMapper {

    List<TransacaoResponse> toTransacaoResponseList(List<Transacao> transacoes);

    /**
     * Método default (Java puro) para montar o DTO agregado.
     */
    default ExtratoResponse toResponse(List<Transacao> transacoes) {
        return new ExtratoResponse(
                toTransacaoResponseList(transacoes)
        );
    }
}
