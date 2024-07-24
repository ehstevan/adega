package com.prova.adega.domain;

import jakarta.persistence.*;
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
public class Cliente {
    @Id
    private String cpf;
    private String nome;

    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras;
}
