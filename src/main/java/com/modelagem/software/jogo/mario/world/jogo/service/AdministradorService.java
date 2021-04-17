package com.modelagem.software.jogo.mario.world.jogo.service;

import com.modelagem.software.jogo.mario.world.jogo.entity.Administrador;
import com.modelagem.software.jogo.mario.world.jogo.repository.AdministradorRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class AdministradorService {

    @Autowired
    private AdministradorRepository repository;

    public List<Administrador> retornarDaBaseTeste(){
        return repository.findAll();
    }

}
