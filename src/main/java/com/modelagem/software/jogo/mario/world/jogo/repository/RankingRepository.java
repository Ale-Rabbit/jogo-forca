package com.modelagem.software.jogo.mario.world.jogo.repository;

import com.modelagem.software.jogo.mario.world.jogo.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RankingRepository extends JpaRepository<Ranking, Integer>{

    public Optional<Ranking> findByNomeJogador(String nomeJogador);

}
