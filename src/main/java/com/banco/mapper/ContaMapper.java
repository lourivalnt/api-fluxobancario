package com.banco.mapper;

import com.banco.domain.entity.Conta;
import com.banco.dto.response.ContaResponse;
import org.mapstruct.Mapper;

/**
 * Mapper responsável por converter ContaEntity em DTOs.
 */
@Mapper(componentModel = "spring")
public interface ContaMapper {

    ContaResponse toResponse(Conta conta);
}

