package com.banco.controller;

import com.banco.dto.response.ExtratoResponse;
import com.banco.service.ExtratoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/extratos")
@RequiredArgsConstructor
public class ExtratoController {

    private final ExtratoService transacaoRService;

    /**
     * Retorna o extrato de uma conta.
     */
    @GetMapping("/{contaId}")
    public ExtratoResponse emitirExtrato(@PathVariable UUID contaId) {
        return transacaoRService.emitirExtrato(contaId);
    }
}

