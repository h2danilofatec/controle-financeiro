package com.fatec.controle_financeiro.controllers;
import com.fatec.controle_financeiro.entities.ContasReceber;
import com.fatec.controle_financeiro.domain.contasreceber.ContasReceberService;

import java.util.List;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contasreceber")
public class ContasReceberController {
    private final ContasReceberService receberService;

    public ContasReceberController(ContasReceberService receberService) {
        this.receberService = receberService;
    }

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ContasReceber receber) {
        try {
            ContasReceber receberCreated = receberService.create(receber);
            return new ResponseEntity<>(receberCreated, HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado: " + ex.getMessage());
        }
    }

    // READ ALL
    @GetMapping("/read")
    public ResponseEntity<List<ContasReceber>> getAllReceber() {
        List<ContasReceber> contasReceber = receberService.findAll();
        return new ResponseEntity<>(contasReceber, HttpStatus.OK);
    }

    // READ BY ID
    @GetMapping("/read/{id}")
    public ResponseEntity<ContasReceber> getById(@PathVariable Long id) {
        return receberService.findById(id)
        .map(receber -> new ResponseEntity<>(receber, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateReceber(@PathVariable Long id, @RequestBody ContasReceber entity) {
        try {
            ContasReceber updated = receberService.update(id, entity);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

     // DELETE
     @DeleteMapping("/delete/{id}")
     public ResponseEntity<?> deleteReceber(@PathVariable Long id) {
        try {
            receberService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
     }
}
