package com.banco.controller;

import com.banco.dto.request.CreditoRequest;
import com.banco.dto.request.CriarContaRequest;
import com.banco.dto.request.DebitoRequest;
import com.banco.dto.request.TransferenciaRequest;
import com.banco.service.ContaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controller responsável por expor endpoints relacionados à conta bancária.
 *
 * Essa camada não contém regra de negócio.
 * Apenas orquestra requisição e resposta.
 */
@RestController
@RequestMapping("/api/contas")
@RequiredArgsConstructor
public class ContaController {

    private final ContaService contaService;

    /**
     * Endpoint para criação de uma nova conta.
     * Acesso típico de um Admin Bancário.
     */
    @PostMapping
    public ResponseEntity<UUID> criarConta(
            @RequestBody @Valid CriarContaRequest request
    ) {
        UUID contaId = contaService.criarConta(request.titular());
        return ResponseEntity.status(HttpStatus.CREATED).body(contaId);
    }

    /**
     * Crédito em conta.
     */
    @PostMapping("/{id}/credito")
    public ResponseEntity<Void> creditar(
            @PathVariable UUID id,
            @RequestBody @Valid CreditoRequest request
    ) {
        contaService.creditar(id, request.valor());
        return ResponseEntity.noContent().build();
    }

    /**
     * Débito em conta.
     */
    @PostMapping("/{id}/debito")
    public ResponseEntity<Void> debitar(
            @PathVariable UUID id,
            @RequestBody @Valid DebitoRequest request
    ) {
        contaService.debitar(id, request.valor());
        return ResponseEntity.noContent().build();
    }

    /**
     * Transferência entre contas.
     */
    @PostMapping("/transferencia")
    public ResponseEntity<Void> transferir(
            @RequestBody @Valid TransferenciaRequest request
    ) {
        contaService.transferir(
                request.contaOrigemId(),
                request.contaDestinoId(),
                request.valor()
        );
        return ResponseEntity.noContent().build();
    }
}

