package com.modelagem.software.jogo.mario.world.jogo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class TesteController {
	
	@GetMapping(value = "teste")
	public ResponseEntity<String> teste(){
		return  ResponseEntity.ok().body("Hello World");
	}

}
