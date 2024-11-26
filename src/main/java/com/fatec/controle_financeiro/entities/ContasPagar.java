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
    @Column(name = "emissao", nullable = false)
    private LocalDate emissao;

    @NotNull(message = "A data de vencimento é obrigatória.")
    @Column(name = "vencimento", nullable = false)
    private LocalDate vencimento;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id", nullable = false)
    @NotNull(message = "O Fornecedor é obrigatório.")
    private Fornecedor fornecedor;

    @NotNull(message = "O valor é obrigatório.")
    @DecimalMin(value = "0.01", inclusive = true)
    @Column(name = "valor", precision = 12, scale = 2, nullable = false)
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

}
