package com.modelagem.software.jogo.mario.world.jogo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ranking {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idRanking;

    private String nomeJogador;

    private Integer pontuacao;

}
