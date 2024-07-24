package com.prova.adega.controller;

import com.prova.adega.service.RecomendacaoVinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recomendacao")
public class RecomendacaoVinhoController {

    @Autowired
    private RecomendacaoVinhoService recomendacaoVinhoService;

    @GetMapping("/{cpf}/tipo")
    public ResponseEntity<String> getRecomendacaoVinho(@PathVariable String cpf) {
        String recomendacaoVinho = recomendacaoVinhoService.getRecomendacaoVinhoByCliente(cpf);
        return ResponseEntity.ok(recomendacaoVinho);
    }
}
