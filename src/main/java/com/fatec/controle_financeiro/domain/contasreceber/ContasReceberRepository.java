package com.fatec.controle_financeiro.domain.contasreceber;
import com.fatec.controle_financeiro.entities.ContasReceber;

// import java.time.LocalDate;
// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContasReceberRepository extends JpaRepository<ContasReceber, Long> { 
    //Optional<ContasReceber> findByDataEmissao(LocalDate emissao);
}