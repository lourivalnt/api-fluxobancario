package com.banco.service;


import java.math.BigDecimal;
import java.util.UUID;

/**
 * Camada de serviço responsável pelas operações
 * financeiras da conta bancária.
 *
 * Define o contrato das regras de negócio.
 */
public interface ContaService {

    UUID criarConta(String titular);

    void creditar(UUID contaId, BigDecimal valor);

    void debitar(UUID contaId, BigDecimal valor);

    void transferir(UUID contaOrigemId, UUID contaDestinoId, BigDecimal valor);
}

