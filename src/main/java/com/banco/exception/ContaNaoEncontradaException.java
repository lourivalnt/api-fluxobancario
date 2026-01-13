package com.banco.exception;

import java.util.UUID;

public class ContaNaoEncontradaException extends BusinessException {

    public ContaNaoEncontradaException(UUID contaId) {
        super("Conta não encontrada para o id: " + contaId);
    }
}
