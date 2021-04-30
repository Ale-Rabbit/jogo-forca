package com.modelagem.software.jogo.mario.world.jogo.service;

import com.modelagem.software.jogo.mario.world.jogo.entity.Administrador;
import com.modelagem.software.jogo.mario.world.jogo.repository.AdministradorRepository;
import com.modelagem.software.jogo.mario.world.jogo.retorno.DtoRetorno;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
public class AdministradorService implements AdmImpl{

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
    
    @Override
	public Administrador save(Administrador adm) {
		return repository.save(adm);
	}
	@Override
	public List<Administrador> findAll() {
		return repository.findAll() ;
	}
	@Override
	public Optional<Administrador> findById(Integer id) {
		return repository.findById(id);
	}
	@Override
	public Administrador update(Administrador adm) {
		return repository.save(adm);
	}
	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

}
