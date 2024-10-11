package com.fatec.controle_financeiro.domain.cliente;

import com.fatec.controle_financeiro.entities.Cliente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    // Busca por nome usando =
    List<Cliente> findAllByName(String name);

    // Busca por nome usando LIKE %nameparam%
    List<Cliente> findAllByNameContaining(String name);

    //// Busca por nome usando LIKE com query manual
    @Query("SELECT c FROM Cliente c WHERE c.name LIKE %:name%")
    List<Cliente> buscarClientesPorNome(@Param("name") String name);
}
