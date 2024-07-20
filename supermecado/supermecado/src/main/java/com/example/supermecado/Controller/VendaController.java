package com.example.supermecado.Controller;

import com.example.supermecado.DTO.CadastraProdutoResponseDTO;
import com.example.supermecado.DTO.VendaResponseDTO;
import com.example.supermecado.Repository.CadastraProdutoRepository;
import com.example.supermecado.Repository.VendaRepository;
import com.example.supermecado.Rest.CadastraProduto;
import com.example.supermecado.Rest.EditarQuantidade;
import com.example.supermecado.Rest.Venda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/supermecado")
public class VendaController {
    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private CadastraProdutoRepository cadastraProdutoRepository;
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/listarvendas")
    public ResponseEntity<?> listar() {
        try{
            List<VendaResponseDTO> pedidoList = vendaRepository.findAll().stream()
                    .map(VendaResponseDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(pedidoList);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar os pedidos: " + e.getMessage());
        }
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/venda")
    public ResponseEntity<String> editarQuantidadeProduto(@RequestBody Venda venda) {
        try {
            // Verifica se há estoque suficiente para cada produto na venda
            for (Long produtoId : venda.getProdutos()) {
                Optional<CadastraProduto> optionalProduto = cadastraProdutoRepository.findById(produtoId);
                if (optionalProduto.isPresent()) {
                    CadastraProduto produto = optionalProduto.get();
                    double quantidadeDisponivel = produto.getQtd();
                    if (quantidadeDisponivel < 1) {
                        return ResponseEntity.badRequest().body("Produto com ID " + produtoId + " sem estoque disponível.");
                    }
                    // Verifica se a quantidade vendida não excede a quantidade disponível
                    // Aqui você pode ajustar a lógica conforme necessário (e.g., permitir venda parcial)
                    if (quantidadeDisponivel < 1) {
                        return ResponseEntity.badRequest().body("Produto com ID " + produtoId + " não possui estoque suficiente.");
                    }
                    // Atualiza a quantidade disponível do produto
                    produto.setQtd(quantidadeDisponivel - 1);
                    cadastraProdutoRepository.save(produto);
                } else {
                    return ResponseEntity.badRequest().body("Produto com ID " + produtoId + " não encontrado.");
                }
            }
            // Salva a venda no banco de dados
            vendaRepository.save(venda);
            return ResponseEntity.ok("Venda realizada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao processar a venda: " + e.getMessage());
        }
    }

}
