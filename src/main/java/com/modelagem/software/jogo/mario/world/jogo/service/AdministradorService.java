package com.modelagem.software.jogo.mario.world.jogo.service;

import com.modelagem.software.jogo.mario.world.jogo.entity.Administrador;
import com.modelagem.software.jogo.mario.world.jogo.repository.AdministradorRepository;
import com.modelagem.software.jogo.mario.world.jogo.retorno.DtoRetorno;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Getter
public class AdministradorService {

    @Autowired
    private AdministradorRepository repository;


    public DtoRetorno autenticar(String username, String password){

        Optional<Administrador> adm = repository.findByUsernameAndPassword(username,
                                                                           password);

        if(adm.isPresent()){
            return DtoRetorno.builder().sucesso(true).build();
        }

        return DtoRetorno.builder().sucesso(false).mensagem("Senha ou usuário incorretos.").build();

    }

}
