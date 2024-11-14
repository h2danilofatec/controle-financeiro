package com.fatec.controle_financeiro.domain.contaspagar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

import com.fatec.controle_financeiro.entities.ContasPagar;

public interface ContasPagarRepository extends JpaRepository<ContasPagar, Long> { 
    Optional<ContasPagar> findByDataEmissao(LocalDate emissao);
}