package com.fatec.controle_financeiro.controllers;
import com.fatec.controle_financeiro.entities.ContasPagar;
import com.fatec.controle_financeiro.domain.contaspagar.ContasPagarService;

import java.util.List;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contaspagar")
public class ContasPagarController {
    private final ContasPagarService pagarService;

    public ContasPagarController(ContasPagarService pagarService){
        this.pagarService = pagarService;
    }

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<ContasPagar> create(@RequestBody ContasPagar pagar) {
        ContasPagar pagarCreated = pagarService.create(pagar);
        return new ResponseEntity<>(pagarCreated, HttpStatus.CREATED);
    }
    
    // READ ALL
    @GetMapping("/read")
    public ResponseEntity<List<ContasPagar>> getAllPagar() {
        List<ContasPagar> contasPagar = pagarService.findAll();
        return new ResponseEntity<>(contasPagar, HttpStatus.OK);
    }
    
    // READ BY ID
    @GetMapping("/read/{id}")
    public ResponseEntity<ContasPagar> getById(@PathVariable Long id) {
        return pagarService.findById(id)
        .map(pagar -> new ResponseEntity<>(pagar, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<ContasPagar> updatePagar(@PathVariable Long id, @RequestBody ContasPagar entity) {
        try {
            ContasPagar updated = pagarService.update(id, entity);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePagar(@PathVariable Long id) {
        try {
            pagarService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
