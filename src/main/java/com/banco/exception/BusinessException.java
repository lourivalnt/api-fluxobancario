package com.banco.exception;

/**
 * Exceção base para erros de regra de negócio.
 *
 * Facilita o tratamento centralizado no ControllerAdvice.
 */
public abstract class BusinessException extends RuntimeException {

    protected BusinessException(String message) {
        super(message);
    }
}
