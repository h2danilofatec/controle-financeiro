package com.fatec.controle_financeiro.domain.fornecedor;
import com.fatec.controle_financeiro.entities.Fornecedor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{
    // Busca por nome usando =
    List<Fornecedor> findAllByName(String name);
    
    // Busca por nome usando LIKE %nameparam%
    List<Fornecedor> findAllByNameContaining(String name);
    
    //// Busca por nome usando LIKE com query manual
    @Query("SELECT c FROM Fornecedor c WHERE c.name LIKE %:name%")
    List<Fornecedor> buscarFornecedoresPorNome(@Param("name") String name);

}