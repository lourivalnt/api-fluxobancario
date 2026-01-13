package com.banco.repository;

import com.banco.domain.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repositório responsável pelo histórico de transações.
 *
 * Usado para geração de extrato.
 */
@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, UUID> {

    /**
     * Retorna o extrato da conta ordenado pela data da transação.
     */
    List<Transacao> findByContaIdOrderByDataHoraDesc(UUID contaId);
}
