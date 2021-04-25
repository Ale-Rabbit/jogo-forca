package com.modelagem.software.jogo.mario.world.jogo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modelagem.software.jogo.mario.world.jogo.entity.Ranking;
import com.modelagem.software.jogo.mario.world.jogo.repository.RankingRepository;

@Service
public class RankingService {

    @Autowired
    private RankingRepository repository;

    public List<Ranking> getRanking(){
        return repository.findAll();
    }

}