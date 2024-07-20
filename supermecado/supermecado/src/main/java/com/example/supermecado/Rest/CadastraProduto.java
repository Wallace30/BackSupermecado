package com.example.supermecado.Rest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "pedidos" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class CadastraProduto {
    @Id @GeneratedValue
    private Long id;
    private int nroproduto;
    private String nome;
    private String descricao;
    private double preco;
    private double qtd;
}
