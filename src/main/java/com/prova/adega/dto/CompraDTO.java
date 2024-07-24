package com.prova.adega.dto;

import lombok.Builder;

@Builder
public record CompraDTO(
        String nomeCliente,
        String cpfCliente,
        String tipoVinho,
        Double preco,
        String safra,
        Integer anoCompra,
        Integer quantidade,
        Double valorTotal
) {
}
