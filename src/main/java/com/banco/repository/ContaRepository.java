package com.banco.repository;

import com.banco.domain.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import jakarta.persistence.LockModeType;
import java.util.Optional;
import java.util.UUID;

/**
 * Repositório responsável por operações de persistência da Conta.
 *
 * Não contém regra de negócio.
 * Apenas acesso a dados.
 */
@Repository
public interface ContaRepository extends JpaRepository<Conta, UUID> {

    /**
     * Busca a conta aplicando lock pessimista de escrita.
     *
     * Esse método é usado em operações críticas como:
     * - débito
     * - transferência
     *
     * Garante que apenas uma transação altere o saldo por vez.
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Conta> findContaById(UUID id);
}
// CENÁRIO PERIGOSO:
// 1: Lê saldo = R$ 1000
// 2: Lê saldo = R$ 1000 (ao mesmo tempo!)
// 1: Debita R$ 500 → saldo = R$ 500
// 2: Debita R$ 300 → saldo = R$ 700 (❌ DEVERIA SER R$ 200!)