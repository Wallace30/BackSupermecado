package com.example.supermecado.Rest;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Table(name = "venda" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Venda {

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    @ElementCollection
    private List<Long> produtos;
    private String formapagamento;
    private String endereco;

}
