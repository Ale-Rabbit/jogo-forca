package com.modelagem.software.jogo.mario.world.jogo.repository;

import com.modelagem.software.jogo.mario.world.jogo.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
