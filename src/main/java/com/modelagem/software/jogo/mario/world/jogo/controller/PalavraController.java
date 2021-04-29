package com.modelagem.software.jogo.mario.world.jogo.controller;

import com.modelagem.software.jogo.mario.world.jogo.entrada.DtoEntradaAtualizarPalavras;
import com.modelagem.software.jogo.mario.world.jogo.retorno.DtoRetorno;
import com.modelagem.software.jogo.mario.world.jogo.retorno.DtoRetornoConsultarPalavras;
import com.modelagem.software.jogo.mario.world.jogo.service.PalavraService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/palavras")
@Getter
public class PalavraController {

    @Autowired
    private PalavraService service;

    @GetMapping
    public ResponseEntity<DtoRetornoConsultarPalavras> consultarPalavras(){
        return ResponseEntity.ok().body(service.consultarPalavras());
    }

    @PostMapping
    public ResponseEntity<DtoRetorno> atualizarPalavras(@RequestBody DtoEntradaAtualizarPalavras entrada){
        return ResponseEntity.ok().body(service.atualizarPalavras(entrada));
    }

}
