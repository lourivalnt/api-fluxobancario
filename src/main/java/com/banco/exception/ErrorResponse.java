package com.banco.exception;

import java.time.LocalDateTime;

/**
 * DTO padrão para respostas de erro da API.
 */
public record ErrorResponse(
        int status,
        String erro,
        String mensagem,
        LocalDateTime timestamp
) {
}
