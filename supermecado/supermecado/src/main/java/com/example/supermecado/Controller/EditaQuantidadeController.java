package com.example.supermecado.Controller;

import com.example.supermecado.Repository.CadastraProdutoRepository;
import com.example.supermecado.Repository.EditarQuantidadeRepository;
import com.example.supermecado.Rest.CadastraProduto;
import com.example.supermecado.Rest.EditarQuantidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/supermecado")
public class EditaQuantidadeController {

    @Autowired
    private CadastraProdutoRepository cadastraProdutoRepository;

    @Autowired
    private EditarQuantidadeRepository editarQuantidadeRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/editaquantidade")
    public ResponseEntity<String> editarQuantidadeProduto(@RequestBody EditarQuantidade editarQuantidade) {
        try {
            // Verifica se o ID existe para evitar uma exceção de persistência
            Optional<CadastraProduto> optionalProduto = cadastraProdutoRepository.findById(editarQuantidade.getId());
            if (optionalProduto.isEmpty()) {
                return new ResponseEntity<>("Produto não encontrado para o ID fornecido", HttpStatus.NOT_FOUND);
            }

            // Atualiza a quantidade do produto
            CadastraProduto produto = optionalProduto.get();
            produto.setQtd(editarQuantidade.getNovaquantidade());
            cadastraProdutoRepository.save(produto);

            return new ResponseEntity<>("Quantidade do produto atualizada com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao atualizar a quantidade do produto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

