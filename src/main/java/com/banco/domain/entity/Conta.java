package com.banco.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Entidade que representa uma Conta Bancária.
 *
 * Essa classe faz parte do domínio e não possui
 * dependência de camadas externas (controller, dto, etc).
 */
@Entity
@Table(name = "conta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Conta {

    /**
     * Identificador único da conta.
     * UUID evita colisões e facilita integração distribuída.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 120)
    private String titular;

    /**
     * Saldo atual da conta.
     * BigDecimal é usado para evitar problemas de precisão.
     */
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal saldo;

    /**
     * Campo de controle de concorrência otimista.
     *
     * Sempre que a conta é atualizada, esse campo é incrementado.
     * Se duas transações tentarem alterar a conta ao mesmo tempo,
     * o Hibernate lança OptimisticLockException.
     */
    @Version
    private Long versao;

    /**
     * Método de fábrica para criar uma conta já com saldo zero.
     * Evita espalhar regras de inicialização pelo sistema.
     */
    public static Conta novaConta(String titular) {
        return Conta.builder()
                .titular(titular)
                .saldo(BigDecimal.ZERO)
                .build();
    }
}
