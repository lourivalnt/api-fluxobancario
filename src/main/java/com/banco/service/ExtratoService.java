package com.banco.service;

import com.banco.dto.response.ExtratoResponse;

import java.util.UUID;

public interface ExtratoService {

    ExtratoResponse emitirExtrato(UUID contaId);
}
