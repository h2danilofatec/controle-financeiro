package com.fatec.controle_financeiro.domain.cliente;
import com.fatec.controle_financeiro.entities.Cliente;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class ClienteService { 

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

   @Transactional
    public Cliente create(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    @Transactional
    public Cliente update(Long id, Cliente Cliente){
        if(!clienteRepository.existsById(id)) {
            throw new IllegalArgumentException("Cliente não encontrado com o ID: " + id);
        }
        Cliente.setId(id);
        return clienteRepository.save(Cliente);
    }
    
    @Transactional
    public void delete(Long id){
        if(!clienteRepository.existsById(id)) {
            throw new IllegalArgumentException("Cliente não encontrado com o ID: " + id);
        }
        clienteRepository.deleteById(id);
    }
}
