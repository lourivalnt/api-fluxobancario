package com.banco.domain.entity;

import com.banco.domain.enums.TipoTransacao;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entidade que representa uma Transação Financeira.
 *
 * Cada crédito, débito ou transferência gera um registro
 * para auditoria e emissão de extrato.
 */
@Entity
@Table(name = "transacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Conta relacionada à transação.
     * Fetch LAZY evita carregamento desnecessário.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "conta_id")
    private Conta conta;

    /**
     * Valor da transação.
     */
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valor;

    /**
     * Tipo da transação (CRÉDITO, DÉBITO, TRANSFERÊNCIA).
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TipoTransacao tipo;

    /**
     * Data e hora do evento.
     * Importante para ordenação do extrato.
     */
    @Column(nullable = false)
    private LocalDateTime dataHora;

    /**
     * Método de fábrica para padronizar criação da transação.
     */
    public static Transacao nova(
            Conta conta,
            BigDecimal valor,
            TipoTransacao tipo
    ) {
        return Transacao.builder()
                .conta(conta)
                .valor(valor)
                .tipo(tipo)
                .dataHora(LocalDateTime.now())
                .build();
    }
}
