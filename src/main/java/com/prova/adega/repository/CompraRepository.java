package com.prova.adega.repository;

import com.prova.adega.domain.Compra;
import com.prova.adega.dto.RecomendacaoVinhoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompraRepository extends JpaRepository<Compra, Long> {
    @Query("SELECT c FROM Compra c JOIN c.produto p JOIN c.cliente cl ORDER BY (p.preco * c.quantidade) ASC")
    List<Compra> findAllComprasOrderedByValor();

    @Query("SELECT c FROM Compra c JOIN c.produto p JOIN c.cliente cl WHERE p.anoCompra = :ano ORDER BY c.quantidade * c.produto.preco DESC")
    List<Compra> findByAnoCompra(@Param("ano") Integer ano);

    @Query("SELECT new com.prova.adega.dto.RecomendacaoVinhoDTO(c.produto.tipoVinho, SUM(c.quantidade)) " +
            "FROM Compra c " +
            "WHERE c.cliente.cpf = :cpf " +
            "GROUP BY c.produto.tipoVinho " +
            "ORDER BY SUM(c.quantidade) DESC")
    List<RecomendacaoVinhoDTO> findTopTipoVinhoByCliente(@Param("cpf") String cpf);
}
