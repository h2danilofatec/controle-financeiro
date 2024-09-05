package com.fatec.controle_financeiro.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.controle_financeiro.entities.User;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    
    //http://localhost:<port>/api/usuario/register
    //POST
    //Parametro: @RequestBody => enviar no corpo da requisicao (body)
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        return "Bem-vindo, " + user.getName() + "! Você tem " + user.getAge() + " anos.";
    }
    
}
