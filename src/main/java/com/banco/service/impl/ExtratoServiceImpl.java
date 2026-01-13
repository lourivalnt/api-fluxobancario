package com.banco.service.impl;

import com.banco.dto.response.ExtratoResponse;
import com.banco.mapper.ExtratoMapper;
import com.banco.repository.TransacaoRepository;
import com.banco.service.ExtratoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExtratoServiceImpl implements ExtratoService {

    private final TransacaoRepository transacaoRepository;
    private final ExtratoMapper extratoMapper;

    @Override
    public ExtratoResponse emitirExtrato(UUID contaId) {

        var transacoes = transacaoRepository
                .findByContaIdOrderByDataHoraDesc(contaId);

        return extratoMapper.toResponse(transacoes);
    }
}
