package com.fatec.controle_financeiro.controllers;
import com.fatec.controle_financeiro.entities.Fornecedor;
import com.fatec.controle_financeiro.domain.fornecedor.FornecedorService;

import java.util.List;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fornecedor")
public class FornecedorController {
    private FornecedorService fornecedorService;
    
    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    // CREATE    
    @PostMapping("/create")
    public ResponseEntity<Fornecedor> create(@RequestBody Fornecedor fornecedor) {
        Fornecedor fornecedorCreated = fornecedorService.create(fornecedor);
        return new ResponseEntity<>(fornecedorCreated, HttpStatus.CREATED);
    }

    // READ ALL
    @GetMapping("/read")
    public ResponseEntity<List<Fornecedor>> getAllFornecedor() {
        List<Fornecedor> fornecedores = fornecedorService.findAll();
        return new ResponseEntity<>(fornecedores, HttpStatus.OK);
    }
    // READ BY ID
    @GetMapping("/read/{id}")
    public ResponseEntity<Fornecedor> getById(@PathVariable Long id) {
        return fornecedorService.findById(id)
        .map(fornecedor -> new ResponseEntity<>(fornecedor, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<Fornecedor> updateFornecedor(@PathVariable Long id, @RequestBody Fornecedor entity) {
        try {
            Fornecedor updated = fornecedorService.update(id, entity);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFornecedor(@PathVariable Long id) {
        try {
            fornecedorService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}