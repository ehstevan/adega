package com.prova.adega.service;

import com.prova.adega.dto.RecomendacaoVinhoDTO;
import com.prova.adega.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecomendacaoVinhoService {

    @Autowired
    private CompraRepository compraRepository;

    public String getRecomendacaoVinhoByCliente(String cpf) {
        List<RecomendacaoVinhoDTO> listaRecomendacoesVinhos = compraRepository.findTopTipoVinhoByCliente(cpf);

        RecomendacaoVinhoDTO recomendacaoVinho = listaRecomendacoesVinhos.stream().findFirst().orElse(null);

        return (recomendacaoVinho != null) ? recomendacaoVinho.tipoVinho() : "Nenhuma recomendação disponível";
    }
}
