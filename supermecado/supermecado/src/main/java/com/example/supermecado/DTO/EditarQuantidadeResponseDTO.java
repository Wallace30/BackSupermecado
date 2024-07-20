package com.example.supermecado.DTO;

import com.example.supermecado.Rest.EditarQuantidade;

public record EditarQuantidadeResponseDTO(Long id, int nroproduto, double novaquantidade) {

    public EditarQuantidadeResponseDTO(EditarQuantidade eq) {
        this(eq.getId(), eq.getNroproduto(), eq.getNovaquantidade());
    }
}
