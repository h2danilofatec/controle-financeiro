package com.fatec.controle_financeiro.controllers;

import com.fatec.controle_financeiro.domain.contaspagar.ContasPagarDto;
import com.fatec.controle_financeiro.domain.contaspagar.ContasPagarRepository;
import com.fatec.controle_financeiro.domain.fornecedor.FornecedorRepository;
import com.fatec.controle_financeiro.entities.ContasPagar;
import com.fatec.controle_financeiro.entities.Fornecedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contas-pagar")
public class ContasPagarController {

    @Autowired
    private ContasPagarRepository contasPagarRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    // Criar uma nova conta a pagar com validação de fornecedor
    @PostMapping
    public ResponseEntity<String> createContasPagar(@RequestBody ContasPagarDto contasPagarDto) {
        try {
            // Verificar se o fornecedor existe
            Optional<Fornecedor> fornecedorData = fornecedorRepository.findById(contasPagarDto.getFornecedorId());
            if (!fornecedorData.isPresent()) {
                return new ResponseEntity<>("Fornecedor não encontrado.", HttpStatus.BAD_REQUEST);
            }

            ContasPagar contasPagar = new ContasPagar();
            contasPagar.setEmissao(contasPagarDto.getEmissao());
            contasPagar.setFornecedor(fornecedorData.get());
            contasPagar.setValor(contasPagarDto.getValor());
            contasPagar.setVencimento(contasPagarDto.getVencimento());    

            // Criar a conta a pagar
            ContasPagar newConta = contasPagarRepository.save(contasPagar);
            return new ResponseEntity<>("Conta a pagar criada com sucesso! ID: " + newConta.getId(), HttpStatus.CREATED);
        } catch (Exception e) {
            //throw new IllegalArgumentException("Erro ao criar conta a pagar.");
            return new ResponseEntity<>("Erro ao criar conta a pagar.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Listar todas as contas a pagar
    @GetMapping
    public ResponseEntity<List<ContasPagar>> getAllContasPagar() {
        try {
            List<ContasPagar> contasPagarList = contasPagarRepository.findAll();
            if (contasPagarList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(contasPagarList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Buscar uma conta a pagar por ID
    @GetMapping("/{id}")
    public ResponseEntity<ContasPagar> getContasPagarById(@PathVariable("id") Long id) {
        Optional<ContasPagar> contaData = contasPagarRepository.findById(id);

        if (contaData.isPresent()) {
            return new ResponseEntity<>(contaData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Atualizar uma conta a pagar por ID
    @PutMapping("/{id}")
    public ResponseEntity<ContasPagar> updateContasPagar(@PathVariable("id") Long id, @RequestBody ContasPagar contasPagar) {
        Optional<ContasPagar> contaData = contasPagarRepository.findById(id);

        if (contaData.isPresent()) {
            ContasPagar existingConta = contaData.get();
            existingConta.setEmissao(contasPagar.getEmissao());
            existingConta.setVencimento(contasPagar.getVencimento());
            existingConta.setFornecedor(contasPagar.getFornecedor());
            existingConta.setValor(contasPagar.getValor());
            return new ResponseEntity<>(contasPagarRepository.save(existingConta), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Excluir uma conta a pagar por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteContasPagar(@PathVariable("id") Long id) {
        try {
            contasPagarRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Excluir todas as contas a pagar
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllContasPagar() {
        try {
            contasPagarRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
