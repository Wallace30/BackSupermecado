package com.example.supermecado.Controller;

import com.example.supermecado.DTO.CadastraProdutoResponseDTO;
import com.example.supermecado.Repository.CadastraProdutoRepository;
import com.example.supermecado.Rest.CadastraProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/supermecado")
public class CadastraController {
    @Autowired
    private CadastraProdutoRepository produtoRepository;
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/listarprodutos")
    public ResponseEntity <?> listar() {
        try{
            List<CadastraProdutoResponseDTO> pedidoList = produtoRepository.findAll().stream()
                    .map(CadastraProdutoResponseDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(pedidoList);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar os pedidos: " + e.getMessage());
        }
    }
    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @PostMapping("/cadastraprodutos")
    public ResponseEntity<String> registerCoffee(@RequestBody CadastraProduto pedido) {
        try {
            CadastraProduto savedProduto = produtoRepository.save(pedido);
            return new ResponseEntity<>("Produto registrado com sucesso!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao registrar o produto ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
