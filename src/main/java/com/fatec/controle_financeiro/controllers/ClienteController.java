package com.fatec.controle_financeiro.controllers;
import com.fatec.controle_financeiro.entities.Cliente;
import com.fatec.controle_financeiro.domain.cliente.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    private List<Cliente> clientes = new ArrayList<>();
    private int proximoId = 1;

    //CREATE    
    @PostMapping("/create")
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {
        Cliente clienteCreated = clienteRepository.save(cliente);
        return new ResponseEntity<>(clienteCreated, HttpStatus.CREATED);
    }

    //READ
    ///READ ALL
    @GetMapping("/read")
    public ResponseEntity<List<Cliente>> getAllCliente() {
        //SELECT * FROM CLIENTES
        List<Cliente> clientes = clienteRepository.findAll();

        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }
    ///READ BY ID
    @GetMapping("/read/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable int id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return new ResponseEntity<>(cliente.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable int id, @RequestBody Cliente entity) {
        Optional<Cliente> clienteAtual = clienteRepository.findById(id);
        if (clienteAtual.isPresent()) {
            entity.setId(id);
            clienteRepository.save(entity);
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }
    }

    //DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable int id) {
        Optional<Cliente> clienteAtual = clienteRepository.findById(id);
        if (clienteAtual.isPresent()) {
            clienteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
