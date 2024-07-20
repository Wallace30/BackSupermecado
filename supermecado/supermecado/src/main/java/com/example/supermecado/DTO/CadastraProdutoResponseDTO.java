package com.example.supermecado.DTO;

import com.example.supermecado.Rest.CadastraProduto;

public record CadastraProdutoResponseDTO(Long id, int nroprduto, String nome, String descricao, double preco, double qtd){
    public CadastraProdutoResponseDTO(CadastraProduto cp)
    {
        this(cp.getId(),cp.getNroproduto(),cp.getNome(),cp.getDescricao(),cp.getPreco(),cp.getQtd());
    }
}
