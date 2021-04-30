package com.modelagem.software.jogo.mario.world.jogo.controller;

import com.modelagem.software.jogo.mario.world.jogo.entrada.DtoEntradaAtualizarPalavras;
import com.modelagem.software.jogo.mario.world.jogo.retorno.DtoRetorno;
import com.modelagem.software.jogo.mario.world.jogo.retorno.DtoRetornoConsultarPalavras;
import com.modelagem.software.jogo.mario.world.jogo.retorno.DtoRetornoConsultarPalavraAleatoria;
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

    @GetMapping(value = "/{categoria}")
    public ResponseEntity<DtoRetornoConsultarPalavraAleatoria> consultarPalavraAleatoria(@PathVariable String categoria){
        return ResponseEntity.ok().body(service.consultarPalavraAleatoria(categoria));
    }

    @GetMapping
    public ResponseEntity<DtoRetornoConsultarPalavraAleatoria> consultarPalavraAleatoria(){
        return ResponseEntity.ok().body(service.consultarPalavraAleatoria());
    }

    @GetMapping(value = "/todas")
    public ResponseEntity<DtoRetornoConsultarPalavras> consultarPalavras(){
        return ResponseEntity.ok().body(service.consultarPalavras());
    }

    @PostMapping
    public ResponseEntity<DtoRetorno> atualizarPalavras(@RequestBody DtoEntradaAtualizarPalavras entrada){
        return ResponseEntity.ok().body(service.atualizarPalavras(entrada));
    }

}
