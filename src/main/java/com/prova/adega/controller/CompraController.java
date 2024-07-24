package com.prova.adega.controller;

import com.prova.adega.dto.CompraDTO;
import com.prova.adega.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompraController {

    private final CompraService compraService;

    @Autowired
    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @GetMapping("/compras")
    public List<CompraDTO> getCompras() {
        return compraService.getComprasOrdenadasPorValor();
    }

    @GetMapping("/maior-compra/{ano}")
    public CompraDTO getMaiorCompraPorAno(@PathVariable Integer ano) {
        return compraService.getMaiorCompraPorAno(ano);
    }
}
