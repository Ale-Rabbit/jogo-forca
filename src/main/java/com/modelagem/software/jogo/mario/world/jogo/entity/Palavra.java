package com.modelagem.software.jogo.mario.world.jogo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Palavra {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idPalavra;

    private String descPv;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fkPvCat")
    private Categoria categoria;
}
