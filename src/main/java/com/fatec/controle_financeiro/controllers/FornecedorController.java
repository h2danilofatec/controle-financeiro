package com.fatec.controle_financeiro.controllers;

import com.fatec.controle_financeiro.entities.Cliente;
import com.fatec.controle_financeiro.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
    
    private List<Cliente> clientes = new ArrayList<>();
    private int proximoId = 1;

    //CRUD = CREATE, READ, UPDATE E DELETE
    
    //CREATE    
    @PostMapping()
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {

        for (Cliente user : clientes) {
            if (user.getName().equals(cliente.getName())) {
                throw new IllegalArgumentException("ja existe nome");
            }
        }

        cliente.setId(proximoId++);
        clientes.add(cliente);

        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }


    //READ
    @GetMapping()
    public ResponseEntity<List<Cliente>> getAllCliente() {
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<Cliente> getById(@PathVariable int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return new ResponseEntity<>(cliente, HttpStatus.OK);
            }
        }
        // Se o cliente não for encontrado, retorna status 404 Not Found
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    //UPDATE
    @PutMapping("{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable int id, @RequestBody Cliente entity) {
         // Percorre a lista de clientes para encontrar o cliente com o ID correspondente
         for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                // Se o cliente for encontrado, atualiza suas informações
                cliente.setName(entity.getName());
                // Retorna o cliente atualizado com status 200 OK
                return new ResponseEntity<>(cliente, HttpStatus.OK);
            }
        }
        // Se o cliente não for encontrado, retorna status 404 Not Found
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable int id) {
        // Percorre a lista de clientes para encontrar o cliente com o ID correspondente
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                // Se o cliente for encontrado, remove-o da lista
                clientes.remove(cliente);
                // Retorna status 204 No Content
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        // Se o cliente não for encontrado, retorna status 404 Not Found
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
