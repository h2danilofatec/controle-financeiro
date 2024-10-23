package com.fatec.controle_financeiro.controllers;

import com.fatec.controle_financeiro.domain.cliente.ClienteRepository;
import com.fatec.controle_financeiro.domain.fornecedor.FornecedorRepository;
import com.fatec.controle_financeiro.entities.Cliente;
import com.fatec.controle_financeiro.entities.Fornecedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/fornecedor")
public class FornecedorController {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    //CRUD = CREATE, READ, UPDATE E DELETE
    
    //CREATE    
    @PostMapping()
    public ResponseEntity<Fornecedor> create(@RequestBody Fornecedor fornecedor) {

        Fornecedor created = fornecedorRepository.save(fornecedor);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }


    //READ
    @GetMapping()
    public ResponseEntity<List<Fornecedor>> getAllFornecedor() {
        List<Fornecedor> fornecedores = fornecedorRepository.findAll();

        return new ResponseEntity<>(fornecedores, HttpStatus.OK);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<Fornecedor> getById(@PathVariable int id) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
        if (fornecedor.isPresent()) {
            return new ResponseEntity<>(fornecedor.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    //UPDATE
    @PutMapping("{id}")
    public ResponseEntity<Fornecedor> updateFornecedor(@PathVariable int id, @RequestBody Fornecedor entity) {
        Optional<Fornecedor> fornecedorAtual = fornecedorRepository.findById(id);
        if (fornecedorAtual.isPresent()) {
            entity.setId(id);
            fornecedorRepository.save(entity);
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFornecedor(@PathVariable int id) {
        Optional<Fornecedor> fornecedorAtual = fornecedorRepository.findById(id);
        if (fornecedorAtual.isPresent()) {
            fornecedorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
