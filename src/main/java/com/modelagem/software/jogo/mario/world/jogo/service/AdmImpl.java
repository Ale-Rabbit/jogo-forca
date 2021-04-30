
package com.modelagem.software.jogo.mario.world.jogo.service;

import java.util.List;
import java.util.Optional;

import com.modelagem.software.jogo.mario.world.jogo.entity.Administrador;

public interface AdmImpl {

	Administrador save(Administrador administrador);

	List<Administrador> findAll();

	Optional<Administrador> findById(Integer id);

	Administrador update(Administrador administrador);

	void deleteById(Integer id);
}