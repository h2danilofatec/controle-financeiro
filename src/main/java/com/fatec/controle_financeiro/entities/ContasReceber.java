package com.fatec.controle_financeiro.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contas_receber")
public class ContasReceber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A data de emissão e obrigatoria.")
    @Column(name = "emissao", nullable = false)
    private LocalDate emissao;

    @NotNull(message = "A data de vencimento e obrigatoria.")
    @Column(name = "vencimento", nullable = false)
    private LocalDate vencimento;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @NotNull(message = "O cliente e obrigatorio.")
    private Cliente cliente;

    @NotNull(message = "O valor e obrigatorio.")
    @DecimalMin(value = "0.01", inclusive = true, message = "O valor deve ser maior que zero.")
    @Column(name = "valor", precision = 12, scale = 2, nullable = false)
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
}
