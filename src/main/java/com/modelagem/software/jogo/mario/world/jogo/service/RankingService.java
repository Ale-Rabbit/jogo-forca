package com.modelagem.software.jogo.mario.world.jogo.service;


import java.util.List;
import java.util.Optional;



import com.modelagem.software.jogo.mario.world.jogo.retorno.DtoRetorno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modelagem.software.jogo.mario.world.jogo.entity.Ranking;
import com.modelagem.software.jogo.mario.world.jogo.repository.RankingRepository;


@Service
public class RankingService {

    @Autowired
    private RankingRepository repository;
    
    

    
    public DtoRetorno salvarRanking(String username, Integer pontuacao){

        Optional<Ranking> jogadorExistente = repository.findByNomeJogador(username);

        if (!jogadorExistente.isPresent()){

            Ranking novoJogador = Ranking.builder()
                                         .nomeJogador(username)
                                         .pontuacao(pontuacao)
                                         .build();

            repository.save(novoJogador);

        } else {

            jogadorExistente.get().setPontuacao(pontuacao);
            repository.save(jogadorExistente.get());

        }

        return DtoRetorno.builder().sucesso(true).build();

    }
    
    
    public List<Ranking> getRanking(){
	    return repository.findTop10ByOrderByPontuacaoDesc();
    }
   

}