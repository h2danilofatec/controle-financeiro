package com.fatec.controle_financeiro.domain.contasreceber;
import com.fatec.controle_financeiro.entities.ContasReceber;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContasReceberRepository extends JpaRepository<ContasReceber, Long> {
    // Busca por ID
    // Busca por cliente 
    // Busca por data emissao
    // Busca por data de vencimento
    List<ContasReceber> findAllByName(String name);
    
    // Create

    // Delete

}