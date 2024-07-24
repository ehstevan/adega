package com.prova.adega.service;

import com.prova.adega.dto.ClienteFielDTO;
import com.prova.adega.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteFielDTO> getTopClientesFieis() {
        return clienteRepository.findTop3ClientesFieis()
                .stream()
                .limit(3)
                .toList();
    }
}

