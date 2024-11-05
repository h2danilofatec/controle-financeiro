package com.fatec.controle_financeiro.domain.contaspagar;
import com.fatec.controle_financeiro.entities.ContasPagar;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ContasPagarService { 

    private final ContasPagarRepository contasPagarRepository;

    public ContasPagarService(ContasPagarRepository contasPagarRepository) {
        this.contasPagarRepository = contasPagarRepository;
    }

    @Transactional
    public ContasPagar create(ContasPagar conta) {
        return contasPagarRepository.save(conta);
    }

    public List<ContasPagar> findAll() {
        return contasPagarRepository.findAll();
    } 

    public Optional<ContasPagar> findById(Long id){
        return contasPagarRepository.findById(id);
    }

    @Transactional
    public ContasPagar update(Long id, ContasPagar conta){
        if (!contasPagarRepository.existsById(id)) {
            throw new IllegalArgumentException("Conta a pagar não encontrada para o ID: " + id);
        }
        conta.setId(id);
        return contasPagarRepository.save(conta);
    }

    @Transactional
    public void delete(long id){
        if (!contasPagarRepository.existsById(id)) {
            throw new IllegalArgumentException("Conta a pagar não encontrada para o ID: " + id);
        }
        contasPagarRepository.deleteById(id);
    }
}