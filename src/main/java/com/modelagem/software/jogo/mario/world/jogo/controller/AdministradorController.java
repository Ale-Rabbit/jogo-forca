package com.modelagem.software.jogo.mario.world.jogo.controller;

import com.modelagem.software.jogo.mario.world.jogo.entity.Administrador;
import com.modelagem.software.jogo.mario.world.jogo.retorno.DtoRetorno;
import com.modelagem.software.jogo.mario.world.jogo.service.AdministradorService;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    
	
	@GetMapping(path = "/list")
	public ResponseEntity<List<Administrador>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).
				body(service.findAll());
	}
	
	@GetMapping(path = "/id/{id}")
	public ResponseEntity<Optional<Administrador>> findById(@PathVariable Integer id){
		return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
	}
	
	@PostMapping(path = "/insert")
    public ResponseEntity<Administrador> create(@RequestBody Administrador adm){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(adm));
    }
	

	@PutMapping(path = "/update/{id}")
    public ResponseEntity<Administrador> update(@RequestBody Administrador adm){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(adm));
    }

	@DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
}
