package com.fatec.controle_financeiro.domain.contaspagar;
import com.fatec.controle_financeiro.entities.ContasPagar;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ContasPagarService { 

    private final ContasPagarRepository contasPagarRepository;

    public ContasPagarService(ContasPagarRepository contasPagarRepository) {
        this.contasPagarRepository = contasPagarRepository;
    }

    @Transactional
    public ContasPagar create(ContasPagar pagar) {
        if(pagar.getEmissao() == null || pagar.getVencimento() == null){
            throw new IllegalArgumentException("As datas não podem ser vazias.");
        }
        if(pagar.getFornecedor() == null){
            throw new IllegalArgumentException("O fornecedor não pode ser vazio.");
        }
        if(pagar.getEmissao().isAfter(pagar.getVencimento())){
            throw new IllegalArgumentException("A data de vencimento não pode ser menor que a data de emissão.");
        }
        BigDecimal valorZero = BigDecimal.ZERO;
        if(pagar.getValor().compareTo(valorZero) <= 0 || pagar.getValor() == null){
            throw new IllegalArgumentException("Valor inválido, o valor deve ser superior à 0 e não pode ser vazio.");
        }
        return contasPagarRepository.save(pagar);
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