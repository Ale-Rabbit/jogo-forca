package com.modelagem.software.jogo.mario.world.jogo.service;

import com.modelagem.software.jogo.mario.world.jogo.entity.Palavra;
import com.modelagem.software.jogo.mario.world.jogo.entity.Ranking;
import com.modelagem.software.jogo.mario.world.jogo.repository.PalavraRepository;
import com.modelagem.software.jogo.mario.world.jogo.repository.RankingRepository;
import com.modelagem.software.jogo.mario.world.jogo.retorno.DtoRetornoIniciar;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Data
public class JogoService {

    @Autowired
    private RankingRepository rankingRepository;

    @Autowired
    private PalavraRepository palavraRepository;

    public DtoRetornoIniciar iniciarJogo(String username, String categoria){

        Optional<Ranking> jogadorExistente = rankingRepository.findByNomeJogador(username);

        if (!jogadorExistente.isPresent()){

            Ranking novoJogador = Ranking.builder()
                                         .nomeJogador(username)
                                         .pontuacao(0)
                                         .build();

            rankingRepository.save(novoJogador);

        }

        if(categoria != null && !categoria.isEmpty()) {

            Optional<List<Palavra>> palavras = palavraRepository.findByCategoriaDescCat(categoria);

            if (palavras.isPresent() && !palavras.get().isEmpty()) {

                if (palavras.get().size() > 1) {

                    return DtoRetornoIniciar.builderDefault()
                            .palavra(retornarPalavraAleatoria(palavras.get()))
                            .build();

                } else {

                    return DtoRetornoIniciar.builderDefault()
                            .palavra(palavras.get().get(0).getDescPv())
                            .build();

                }

            }

        } else {

            List<Palavra> palavras = palavraRepository.findAll();

            if (palavras != null && !palavras.isEmpty()) {

                return DtoRetornoIniciar.builderDefault()
                        .palavra(retornarPalavraAleatoria(palavras))
                        .build();

            }

        }

        return new DtoRetornoIniciar(false,
                                                "Nenhuma palavra encontrada para categoria " + categoria.toString());

    }


    public DtoRetornoIniciar iniciarJogo(String username){
        return iniciarJogo(username, null);
    }

    private String retornarPalavraAleatoria(List<Palavra> palavras){

        int quantidadeDePalavras = palavras.size();

        Palavra palavraAleatoria = palavras.get(new Random().nextInt(quantidadeDePalavras));
        return palavraAleatoria.getDescPv();

    }
}
