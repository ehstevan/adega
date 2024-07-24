package com.prova.adega.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Produto {
    @Id
    private Long codigo;
    @JsonProperty("tipo_vinho")
    private String tipoVinho;
    private Double preco;
    private String safra;
    @JsonProperty("ano_compra")
    private Integer anoCompra;

    @OneToMany(mappedBy = "produto")
    private List<Compra> compras;
}
