package com.modelagem.software.jogo.mario.world.jogo.controller;



import java.util.List;

import com.modelagem.software.jogo.mario.world.jogo.retorno.DtoRetorno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.modelagem.software.jogo.mario.world.jogo.entity.Ranking;
import com.modelagem.software.jogo.mario.world.jogo.service.RankingService;

@RestController
@RequestMapping(value = "/ranking")
public class RankingController {

    @Autowired
    private RankingService service;

    @PostMapping(value = "/{username}/{pontuacao}")
    public DtoRetorno salvarRanking(@PathVariable String username,
                                    @PathVariable Integer pontuacao){
        return service.salvarRanking(username, pontuacao);
    }

    @GetMapping
    public List<Ranking> findTop10(){
        return service.getRanking();
    }

}
