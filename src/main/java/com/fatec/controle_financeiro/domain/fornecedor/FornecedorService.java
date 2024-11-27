package com.fatec.controle_financeiro.domain.fornecedor;
import com.fatec.controle_financeiro.entities.Fornecedor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class FornecedorService { 

    private final FornecedorRepository fornecedorRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

   @Transactional
    public Fornecedor create(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public List<Fornecedor> findAll() {
        return fornecedorRepository.findAll();
    }

    public Optional<Fornecedor> findById(Long id) {
        return fornecedorRepository.findById(id);
    }

    @Transactional
    public Fornecedor update(Long id, Fornecedor fornecedor){
        if(!fornecedorRepository.existsById(id)) {
            throw new IllegalArgumentException("Fornecedor não encontrado com o ID: " + id);
        }
        fornecedor.setId(id);
        return fornecedorRepository.save(fornecedor);
    }
    
    @Transactional
    public void delete(Long id){
        if(!fornecedorRepository.existsById(id)) {
            throw new IllegalArgumentException("Fornecedor não encontrado com o ID: " + id);
        }
        fornecedorRepository.deleteById(id);
    }
}
