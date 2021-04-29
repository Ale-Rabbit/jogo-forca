package com.modelagem.software.jogo.mario.world.jogo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Palavra {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idPalavra;

    private String descPv;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "fkPvCat")
    private Categoria categoria;

}
