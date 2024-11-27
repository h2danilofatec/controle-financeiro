package com.fatec.controle_financeiro.entities;

import jakarta.persistence.*;
import java.util.List;

import io.micrometer.common.lang.Nullable;

@Entity
@Table(name = "fornecedor")
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nullable
    @Column(length = 60)
    private String name;

    @OneToMany(mappedBy = "fornecedor")
    private List<ContasPagar> contasPagar;

    public Fornecedor() { } // Empty Constructor

    public Fornecedor(Long id, String name) {
        this.id = id;
        this.name = name;
    } // Constructor

    public Long getId() { 
        return id; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
