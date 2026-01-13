package com.banco.service.impl;

import com.banco.domain.entity.Conta;
import com.banco.domain.entity.Transacao;
import com.banco.repository.ContaRepository;
import com.banco.repository.TransacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/*
* “Aqui testo apenas a regra de negócio, isolando dependências com Mockito
* e sem subir contexto Spring, o que deixa o teste mais rápido.”
* */
@ExtendWith(MockitoExtension.class)
class ContaServiceImplTest {

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private TransacaoRepository transacaoRepository;

    @InjectMocks
    private ContaServiceImpl contaService;

    private Conta conta;
    private UUID contaId;


    /*
    * Aqui é como  arrumar a mesa antes de começar a trabalhar
    * */
    @BeforeEach
    void Setup() {
        contaId = UUID.randomUUID(); // Gera um número aleatório
        conta = Conta.builder() // Constroi uma conta de exemplo
                .id(contaId)
                .titular("Cliente Teste")
                .saldo(new BigDecimal("100.00"))
                .build();
    }


    /**
     * ✅ TESTE 1: CRÉDITO NA CONTA
     * Cenário: "Cliente deposita R$ 50,00 numa conta com R$ 100,00"
     * Resultado esperado: "Saldo final deve ser R$ 150,00"
     */
    @Test
    void deveCreditarValorContaSaldoSuficiente() {
//      1. Quando o sistema procurar a conta, retorne a conta de teste
        when(contaRepository.findContaById(contaId))
                .thenReturn(Optional.of(conta));

//      2. Execute o depósito de R$ 50,00
        contaService.creditar(contaId, new BigDecimal("50.00"));

//      Verifique se o saldo ficou R$ 150,00, compara apenas o VALOR NUMÉRICO
        assertThat(conta.getSaldo()).isEqualByComparingTo("150.00");

//        Confirme que uma movimentação foi registrada
        verify(transacaoRepository).save(any(Transacao.class));
    }

    @Test
    void deveDebitarValorContaSaldoSuficiente() {
        when(contaRepository.findContaById(contaId))
                .thenReturn(Optional.of(conta));

        contaService.debitar(contaId, new BigDecimal("40.00"));

        assertThat(conta.getSaldo()).isEqualByComparingTo("60.00");

        verify(transacaoRepository).save(any(Transacao.class));
    }

    @Test
    void deveLancarExcecaoQuandoSaldoinsuficiente() {
//        Uso do assertThatThrownBy
    }
}