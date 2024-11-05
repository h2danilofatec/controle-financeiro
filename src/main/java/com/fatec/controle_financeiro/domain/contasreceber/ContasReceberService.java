package com.fatec.controle_financeiro.domain.contasreceber;

import com.fatec.controle_financeiro.entities.ContasReceber;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ContasReceberService {

    private final ContasReceberRepository contasReceberRepository;

    public ContasReceberService(ContasReceberRepository contasReceberRepository) {
        this.contasReceberRepository = contasReceberRepository;
    }

    @Transactional
    public ContasReceber create(ContasReceber conta) {
        return contasReceberRepository.save(conta);
    }

    public List<ContasReceber> findAll() {
        return contasReceberRepository.findAll();
    }

    public Optional<ContasReceber> findById(Long id) {
        return contasReceberRepository.findById(id);
    }

    @Transactional
    public ContasReceber update(Long id, ContasReceber conta) {
        if (!contasReceberRepository.existsById(id)) {
            throw new IllegalArgumentException("Conta a receber não encontrada para o ID: " + id);
        }
        conta.setId(id);
        return contasReceberRepository.save(conta);
    }

    @Transactional
    public void delete(Long id) {
        if (!contasReceberRepository.existsById(id)) {
            throw new IllegalArgumentException("Conta a receber não encontrada para o ID: " + id);
        }
        contasReceberRepository.deleteById(id);
    }
}