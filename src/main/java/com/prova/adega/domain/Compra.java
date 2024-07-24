package com.prova.adega.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "cpf_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "codigo_produto")
    private Produto produto;

    @Transient
    @JsonProperty("codigo")
    private Long codigoProduto;
}
