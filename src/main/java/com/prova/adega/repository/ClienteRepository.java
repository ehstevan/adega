package com.prova.adega.repository;

import com.prova.adega.domain.Cliente;
import com.prova.adega.dto.ClienteFielDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("SELECT new com.prova.adega.dto.ClienteFielDTO(cl.nome, cl.cpf, SUM(c.quantidade * p.preco)) " +
            "FROM Cliente cl " +
            "JOIN cl.compras c " +
            "JOIN c.produto p " +
            "GROUP BY cl.nome, cl.cpf " +
            "ORDER BY SUM(c.quantidade * p.preco) DESC")
    List<ClienteFielDTO> findTop3ClientesFieis();
}
