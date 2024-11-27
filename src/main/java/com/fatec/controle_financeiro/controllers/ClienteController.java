package com.fatec.controle_financeiro.controllers;
import com.fatec.controle_financeiro.entities.Cliente;
import com.fatec.controle_financeiro.domain.cliente.ClienteService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    
    // CREATE    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Cliente cliente) {
        try {
            Cliente clienteCreated = clienteService.create(cliente);
            return new ResponseEntity<>(clienteCreated, HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // READ ALL
    @GetMapping("/read")
    public ResponseEntity<List<Cliente>> getAllCliente() {
        List<Cliente> clientes = clienteService.findAll();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }
    // READ BY ID
    @GetMapping("/read/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable Long id) {
        return clienteService.findById(id)
        .map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable Long id, @RequestBody Cliente entity) {
        try {
            Cliente updated = clienteService.update(id, entity);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
        try {
            clienteService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}