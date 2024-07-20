package com.example.supermecado.DTO;

import com.example.supermecado.Rest.Venda;

import java.util.List;

public record VendaResponseDTO(String nome, List<Long> produtos, String formapagamento,String Endereco) {

    public VendaResponseDTO(Venda venda) {
        this(venda.getNome(), venda.getProdutos(), venda.getFormapagamento(), venda.getEndereco());
    }
}
