package com.modelagem.software.jogo.mario.world.jogo.controller;

import com.modelagem.software.jogo.mario.world.jogo.retorno.DtoRetornoIniciar;
import com.modelagem.software.jogo.mario.world.jogo.service.JogoService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/jogo")
@Getter
public class JogoController {

    @Autowired
    private JogoService service;

    @PostMapping(value = "/iniciar/{username}/{categoria}")
    public ResponseEntity<DtoRetornoIniciar> login(@PathVariable String username,
                                                   @PathVariable String categoria){

        return ResponseEntity.ok().body(service.iniciarJogo(username, categoria));

    }
}
