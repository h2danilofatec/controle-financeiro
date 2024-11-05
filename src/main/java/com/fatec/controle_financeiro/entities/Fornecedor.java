package com.fatec.controle_financeiro.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "fornecedores")
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome do Fornecedor é obrigatório.")
    @Column(nullable = false, length = 60)
    private String name;

    @OneToMany(mappedBy = "fornecedor")
    private List<ContasPagar> contasPagar;

    public Fornecedor() { }

    public Fornecedor(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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
