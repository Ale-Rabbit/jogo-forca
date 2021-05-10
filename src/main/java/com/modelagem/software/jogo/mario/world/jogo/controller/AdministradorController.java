package com.modelagem.software.jogo.mario.world.jogo.controller;

import com.modelagem.software.jogo.mario.world.jogo.entity.Administrador;
import com.modelagem.software.jogo.mario.world.jogo.retorno.DtoRetorno;
import com.modelagem.software.jogo.mario.world.jogo.service.AdministradorService;
import com.modelagem.software.jogo.mario.world.jogo.retorno.RetornoErroAdm;

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
		String compuser;
		String comppass;
		
		compuser = adm.getUsername();
		comppass = adm.getPassword();
		//Tirando espaço do cadastro
		compuser = compuser.replace(" ", "");
		comppass = comppass.replace(" ", "");
		//valida se os campos username e password estao sendo inseridos sem espaço e nao vazios
		if(adm.getUsername() != compuser || adm.getPassword() != comppass || adm.getPassword().isEmpty() || adm.getUsername().isEmpty()) {
			return new ResponseEntity(new RetornoErroAdm("Campos 'Username' e/ou 'Password não foram preenchidos corretamente!"), HttpStatus.NOT_FOUND);
		}
		//valida tamanho minimo para username
		if(adm.getUsername().length() < 4) {
			return new ResponseEntity(new RetornoErroAdm("Campo Username deve ter no mínimo 4 caracteres!"), HttpStatus.NOT_FOUND);
		}
		//valida tamanho minimo para password
		if(adm.getPassword().length() < 4) {
			return new ResponseEntity(new RetornoErroAdm("Campo Password deve ter no mínimo 4 caracteres!"), HttpStatus.NOT_FOUND);
		}
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(adm));
    }
	
	@PutMapping(path = "/update/{id}")
    public ResponseEntity<Administrador> update(@RequestBody Administrador adm){
		if(adm.getIdAdministrador() == null) {
			return new ResponseEntity(new RetornoErroAdm("Deve ser informado o Id para atualizar!"), HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.status(HttpStatus.OK).body(service.update(adm));
    }

	@DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
}
