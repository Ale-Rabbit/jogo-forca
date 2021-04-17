package com.modelagem.software.jogo.mario.world.jogo.controller;

import com.modelagem.software.jogo.mario.world.jogo.service.AdministradorService;
import com.modelagem.software.jogo.mario.world.jogo.entity.Administrador;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/")
@Getter
public class TesteController {

	@Autowired
	private AdministradorService service;

	@GetMapping(value = "teste")
	public ResponseEntity<String> teste(){
		return  ResponseEntity.ok().body("Hello World");
	}

	@GetMapping(value = "adm")
	public ResponseEntity<List<Administrador>> retornarDaBaseTeste(){
		return  ResponseEntity.ok().body(service.retornarDaBaseTeste());
	}

}
