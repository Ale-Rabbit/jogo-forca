package com.modelagem.software.jogo.mario.world.jogo.controller;

import com.modelagem.software.jogo.mario.world.jogo.retorno.DtoRetorno;
import com.modelagem.software.jogo.mario.world.jogo.service.AdministradorService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/adm")
@Getter
public class AdministradorController {

    @Autowired
    private AdministradorService service;

    @PostMapping(value = "/login")
    public ResponseEntity<DtoRetorno> login(@RequestHeader() String username,
                                            @RequestHeader() String password){

        return ResponseEntity.ok().body(service.autenticar(username, password));

    }

}
