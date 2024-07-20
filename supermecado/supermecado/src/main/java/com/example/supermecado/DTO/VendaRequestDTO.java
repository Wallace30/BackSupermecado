package com.example.supermecado.DTO;

import java.util.List;

public record VendaRequestDTO(String nome, List<Long> produtos,String formapagamento,String Endereco) {
}
