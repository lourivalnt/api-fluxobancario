package com.banco.service.impl;

import com.banco.domain.entity.Conta;
import com.banco.domain.entity.Transacao;
import com.banco.domain.enums.TipoTransacao;
import com.banco.repository.ContaRepository;
import com.banco.repository.TransacaoRepository;
import com.banco.service.ContaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Implementação das regras de negócio da conta bancária.
 *
 * Todas as operações financeiras são transacionais,
 * garantindo atomicidade e consistência.
 */
@Service
@RequiredArgsConstructor
public class ContaServiceImpl implements ContaService {

    private final ContaRepository contaRepository;
    private final TransacaoRepository transacaoRepository;

    @Override
    @Transactional
    public UUID criarConta(String titular) {

        Conta conta = Conta.novaConta(titular);

        Conta contaSalva = contaRepository.save(conta);

        return contaSalva.getId();
    }

    @Override
    @Transactional
    public void creditar(UUID contaId, BigDecimal valor) {

        Conta conta = buscarConta(contaId);

        conta.setSaldo(conta.getSaldo().add(valor));

        transacaoRepository.save(
                Transacao.nova(conta, valor, TipoTransacao.CREDITO)
        );
    }

    /**
     * Débito em conta com validação de saldo.
     */
    @Override
    @Transactional
    public void debitar(UUID contaId, BigDecimal valor) {

        Conta conta = buscarConta(contaId);

        validarSaldo(conta, valor);

        conta.setSaldo(conta.getSaldo().subtract(valor));

        transacaoRepository.save(
                Transacao.nova(conta, valor, TipoTransacao.DEBITO)
        );
    }

    /**
     * Transferência entre contas.
     *
     * A transação garante que:
     * - ou tudo acontece
     * - ou nada é persistido
     */
    @Override
    @Transactional
    public void transferir(UUID contaOrigemId, UUID contaDestinoId, BigDecimal valor) {

        Conta origem = buscarConta(contaOrigemId);
        Conta destino = buscarConta(contaDestinoId);

        validarSaldo(origem, valor);

        origem.setSaldo(origem.getSaldo().subtract(valor));
        destino.setSaldo(destino.getSaldo().add(valor));

        transacaoRepository.save(
                Transacao.nova(origem, valor, TipoTransacao.TRANSFERENCIA_ENVIO)
        );

        transacaoRepository.save(
                Transacao.nova(destino, valor, TipoTransacao.TRANSFERENCIA_RECEBIMENTO)
        );
    }

    /**
     * Busca a conta aplicando lock pessimista.
     */
    private Conta buscarConta(UUID contaId) {
        return contaRepository.findContaById(contaId)
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));
    }

    /**
     * Regra de negócio: não permitir saldo negativo.
     */
    private void validarSaldo(Conta conta, BigDecimal valor) {
        if (conta.getSaldo().compareTo(valor) < 0) {
            throw new IllegalStateException("Saldo insuficiente");
        }
    }
}

