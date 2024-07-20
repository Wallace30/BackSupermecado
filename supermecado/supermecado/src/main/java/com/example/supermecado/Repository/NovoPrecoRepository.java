package com.example.supermecado.Repository;

import com.example.supermecado.Rest.EditarPreco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NovoPrecoRepository extends JpaRepository<EditarPreco,Long> {
}
