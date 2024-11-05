package com.fatec.controle_financeiro.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contas_receber")
public class ContasReceber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A data de emissão é obrigatória.")
    @Column(name = "emissao")
    private LocalDate emissao;

    @NotNull(message = "A data de vencimento é obrigatória.")
    @Column(name = "vencimento")
    private LocalDate vencimento;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @NotNull(message = "O cliente é obrigatório.")
    private Cliente cliente;

    @NotNull(message = "O valor é obrigatório.")
    @DecimalMin(value = "0.01", inclusive = true, message = "O valor deve ser maior que zero.")
    @Column(name = "valor", precision = 12, scale = 2)
    private BigDecimal valor;
    
    public ContasReceber() { } // Constructor
    
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public LocalDate getEmissao(){
        return emissao;
    }

    public void setEmissao(LocalDate emissao){
        this.emissao = emissao;
    }

    public LocalDate getVencimento(){
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento){
        this.vencimento = vencimento;
    }

    public Cliente getCliente(){
        return cliente;
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public BigDecimal getValor(){
        return valor;
    }

    public void setValor(BigDecimal valor){
        this.valor = valor;
    }

    @PrePersist
    @PreUpdate
    private void validarDatar(){
        if(emissao != null && vencimento != null && emissao.isAfter(vencimento)){
            throw new IllegalArgumentException("A data de emissão não pode ser posterior à data de vencimento.");
        }
    }
}
