package com.fatec.controle_financeiro.domain.fornecedor;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.controle_financeiro.entities.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {
    
}
