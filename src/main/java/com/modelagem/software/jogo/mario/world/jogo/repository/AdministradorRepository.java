package com.modelagem.software.jogo.mario.world.jogo.repository;

import com.modelagem.software.jogo.mario.world.jogo.entity.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {

    public Optional<Administrador> findByUsernameAndPassword(String username, String password);

}
