package com.banco.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * DTO usado para criação de uma nova conta.
 *
 * Representa apenas os dados necessários
 * para a operação, sem expor a entidade.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContaRequest {

    @NotBlank(message = "O nome do titular é obrigatório")
    private String titular;
}
