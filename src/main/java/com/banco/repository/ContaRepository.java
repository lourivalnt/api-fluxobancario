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
     * Esse método é usado em operações como:
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

//OUTRA SITUAÇÂO
// SALDO INICIAL: R$ 1000
// Thread 1 (Caixa A): Quer sacar R$ 800
// Thread 2 (Caixa B): Quer sacar R$ 600

// SEM CONTROLE DE CONCORRÊNCIA:
/*Thread 1: Lê saldo → R$ 1000
Thread 2: Lê saldo → R$ 1000 (leu ao mesmo tempo!)
Thread 1: Calcula novo saldo → 1000 - 800 = R$ 200
Thread 2: Calcula novo saldo → 1000 - 600 = R$ 400
Thread 1: Salva R$ 200
Thread 2: Salva R$ 400 (SOBRESCREVE!)*/

// RESULTADO FINAL: R$ 400 (❌ DEVERIA SER R$ -400 - CONTA NEGATIVADA!)