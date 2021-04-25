package com.modelagem.software.jogo.mario.world.jogo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modelagem.software.jogo.mario.world.jogo.entity.Ranking;
import com.modelagem.software.jogo.mario.world.jogo.service.RankingService;

@RestController
public class RankingController {

    @Autowired
    private RankingService service;

    @GetMapping("/ranking")
    public List<Ranking> findAllRanking(){
        return service.getRanking();
    }

}
