package com.prova.adega.service;

import com.prova.adega.domain.Compra;
import com.prova.adega.dto.CompraDTO;
import com.prova.adega.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService {

    private final CompraRepository compraRepository;

    @Autowired
    public CompraService(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    public List<CompraDTO> getComprasOrdenadasPorValor() {
        List<Compra> compras = compraRepository.findAllComprasOrderedByValor();

        return compras.stream()
                .map(compra -> CompraDTO.builder()
                        .nomeCliente(compra.getCliente().getNome())
                        .cpfCliente(compra.getCliente().getCpf())
                        .tipoVinho(compra.getProduto().getTipoVinho())
                        .preco(compra.getProduto().getPreco())
                        .safra(compra.getProduto().getSafra())
                        .anoCompra(compra.getProduto().getAnoCompra())
                        .quantidade(compra.getQuantidade())
                        .valorTotal(compra.getProduto().getPreco() * compra.getQuantidade())
                        .build()).toList();
    }

    public CompraDTO getMaiorCompraPorAno(Integer ano) {
        List<Compra> listaMaioresCompras = compraRepository.findByAnoCompra(ano);

        Compra maiorCompra = listaMaioresCompras.stream().findFirst().orElse(null);

        return (maiorCompra != null) ?
                CompraDTO.builder()
                        .nomeCliente(maiorCompra.getCliente().getNome())
                        .cpfCliente(maiorCompra.getCliente().getCpf())
                        .tipoVinho(maiorCompra.getProduto().getTipoVinho())
                        .preco(maiorCompra.getProduto().getPreco())
                        .safra(maiorCompra.getProduto().getSafra())
                        .anoCompra(maiorCompra.getProduto().getAnoCompra())
                        .quantidade(maiorCompra.getQuantidade())
                        .valorTotal(maiorCompra.getProduto().getPreco() * maiorCompra.getQuantidade())
                        .build() : null;
    }
}
