package com.fatec.controle_financeiro.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contas_pagar")
public class ContasPagar {
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
    @JoinColumn(name = "fornecedor_id")
    @NotNull(message = "O Fornecedor é obrigatório.")
    private Fornecedor fornecedor;

    @NotNull(message = "O valor é obrigatório.")
    @DecimalMin(value = "0.01", inclusive = true, message = "O valor deve ser maior que zero.")
    @Column(name = "valor", precision = 12, scale = 2)
    private BigDecimal valor;

    public ContasPagar() {} // Constructor

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getEmissao() {
        return emissao;
    }

    public void setEmissao(LocalDate emissao) {
        this.emissao = emissao;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @PrePersist
    @PreUpdate
    private void validarData(){
        if(emissao != null && vencimento != null && emissao.isAfter(vencimento)){
            throw new IllegalArgumentException("A data de emissão não pode ser posterior à data de vencimento.");
        }
    }
}
