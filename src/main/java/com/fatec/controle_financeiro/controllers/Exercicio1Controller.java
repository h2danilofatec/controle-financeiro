package com.fatec.controle_financeiro.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


//http://localhost:8090/api/exercicios1
@RestController
@RequestMapping("/api/exercicios1")
public class Exercicio1Controller {
    
    @GetMapping()
    public String HelloWorld() {
        return "Hello";
    }

    @GetMapping("/hello1")
    public String HelloWorld1() {
        return "Hello1";
    }

    //@PathVariable = anotacao para definir que a variavel nome que é do tipo String será recebida pelo
    //parametro {nome}
    @GetMapping("/reverter-nome/{nome}")
    String reverterNome(@PathVariable String nome) {
        return new StringBuilder(nome).reverse().toString();
    }

    ///api/exercicios1/2/par-ou-impar => @GetMapping("/{numero}/par-ou-impar")
    ///api/exercicios1/par-ou-impar/2 => @GetMapping("/par-ou-impar/{numero}")
    @GetMapping("/par-ou-impar/{numero}")
    String verificarParOuImpar(@PathVariable Integer numero) {
        if (numero % 2 == 0) {
            return "Par";
        } else {
            return "Ímpar";
        }
    }

    //1 - @GetMapping("/contar-letras/{texto}")
    @GetMapping("/contar-letras/{texto}")
    String contarLetras(@PathVariable String texto) {
        int contador = texto.length();
        return "O texto tem " + contador + " letras.";
    }

    //2 - @GetMapping("/idade-com-parametro-tipo-integer/{paramIdade}")
    //Idade < 12 => retorna "CRIANCA"
    //Idade <= 18 => retorna "ADOLESCENTE"
    //Idade <= 60 => retorna "ADULTO"
    //Acima 60 => retorna "IDOSO"
    //Idade invalida => retorna "idade invalida"
    @GetMapping("/idade-com-parametro-tipo-integer/{paramIdade}")
    String getIdadeComParametroTipoInteger(@PathVariable Integer paramIdade) {
        try {
            // Tenta converter a string para um BigInteger
            int idade = paramIdade;
            if (idade < 0){
                throw new NumberFormatException();
            }

            if (idade < 12) {
                return "Crianca";
            } else if (idade <= 18) {
                return "Adolescente";
            } else if (idade <= 60) {
                return "Adulto";
            } else {
                return "Idoso";
            }
        } catch (NumberFormatException e) {
            // Se a conversão falhar, significa que a string não é um número válido
            return "idade invalida";
        }
    }




}
