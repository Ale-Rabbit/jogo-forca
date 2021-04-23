package com.modelagem.software.jogo.mario.world.jogo.repository;

import com.modelagem.software.jogo.mario.world.jogo.entity.Palavra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PalavraRepository extends JpaRepository<Palavra, Integer>{

    public Optional<List<Palavra>> findByCategoriaDescCat(String categoria);

}
