package com.modelagem.software.jogo.mario.world.jogo.controller;

import com.modelagem.software.jogo.mario.world.jogo.entrada.DtoEntradaAtualizarCategorias;
import com.modelagem.software.jogo.mario.world.jogo.retorno.DtoRetorno;
import com.modelagem.software.jogo.mario.world.jogo.retorno.DtoRetornoConsultarCategorias;
import com.modelagem.software.jogo.mario.world.jogo.service.CategoriaService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/categorias")
@Getter
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping
    public ResponseEntity<DtoRetornoConsultarCategorias> consultarCategorias(){
        return ResponseEntity.ok().body(service.consultarCategorias());
    }

    @PostMapping
    public ResponseEntity<DtoRetorno> atualizarCategorias(@RequestBody DtoEntradaAtualizarCategorias entrada){
        return ResponseEntity.ok().body(service.atualizarCategorias(entrada));
    }

}
