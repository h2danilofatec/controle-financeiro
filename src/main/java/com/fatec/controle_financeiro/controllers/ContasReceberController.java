package com.fatec.controle_financeiro.controllers;
import com.fatec.controle_financeiro.entities.ContasReceber;
import com.fatec.controle_financeiro.domain.contasreceber.ContasReceberRepository;
import com.fatec.controle_financeiro.domain.contasreceber.ContasReceberService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/contasreceber")
public class ContasReceberController {
    
    @Autowired
    private ContasReceberRepository receberRepository;

    private List<ContasReceber> receber = new ArrayList<>();
    private int proximoId = 1;

    // CREATE
    @PostMapping()
    public ResponseEntity<ContasReceber> create(@RequestBody ContasReceber receber){
        ContasReceber receberCreated = receberRepository.save(receber);
        return new ResponseEntity<>(receberCreated, HttpStatus.CREATED);
    }

    // READ (SELECT)
    @GetMapping()
    public ResponseEntity<List<ContasReceber>> getAllReceber(){
        List<ContasReceber> contasReceber = receberRepository.findAll();
        return new ResponseEntity<>(contasReceber, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ContasReceber> getById(@PathVariable Long id){
        Optional<ContasReceber> receber = receberRepository.findById(id);
        if(receber.isPresent()){
            return new ResponseEntity<>(receber.get(), HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // UPDATE
    @PutMapping("{id}")
    public ResponseEntity<ContasReceber> updateReceber(@PathVariable Long id, @RequestBody ContasReceber entity){
        Optional<ContasReceber> receberAtual = receberRepository.findById(id);
        if(receberAtual.isPresent()){
            entity.setId(id);
            receberRepository.save(entity);
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceber(@PathVariable Long id){
        Optional<ContasReceber> receberAtual = receberRepository.findById(id);
        if (receberAtual.isPresent()) {
            receberRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
