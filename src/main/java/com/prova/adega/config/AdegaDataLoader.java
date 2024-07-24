package com.prova.adega.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prova.adega.domain.Cliente;
import com.prova.adega.domain.Produto;
import com.prova.adega.repository.ClienteRepository;
import com.prova.adega.repository.CompraRepository;
import com.prova.adega.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AdegaDataLoader implements CommandLineRunner {
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;
    private final CompraRepository compraRepository;

    @Autowired
    public AdegaDataLoader(ProdutoRepository produtoRepository, ClienteRepository clienteRepository, CompraRepository compraRepository) {
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
        this.compraRepository = compraRepository;
    }

    @Override
    public void run(String... args) {
        ObjectMapper objectMapper = new ObjectMapper();
        loadProdutos(objectMapper);
        loadClientesECompras(objectMapper);
    }

    private void loadProdutos(ObjectMapper objectMapper) {
        try (InputStream produtoStream = new ClassPathResource("produtos.json").getInputStream()) {
            List<Produto> produtos = objectMapper.readValue(produtoStream, new TypeReference<>() {
            });
            produtoRepository.saveAll(produtos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar produtos: ", e);
        }
    }

    private void loadClientesECompras(ObjectMapper objectMapper) {
        try (InputStream clienteStream = new ClassPathResource("clientes.json").getInputStream()) {
            List<Cliente> clientes = objectMapper.readValue(clienteStream, new TypeReference<>() {
            });

            Map<Long, Produto> produtoMap = produtoRepository.findAll()
                    .stream()
                    .collect(Collectors.toMap(Produto::getCodigo, produto -> produto));

            compraRepository.deleteAll();

            clientes.forEach(cliente -> {
                clienteRepository.save(cliente);
                cliente.getCompras().forEach(compra -> {
                    compra.setCliente(cliente);
                    compra.setProduto(produtoMap.get(compra.getCodigoProduto()));
                    compraRepository.save(compra);
                });
            });
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar clientes e compras: ", e);
        }
    }
}
