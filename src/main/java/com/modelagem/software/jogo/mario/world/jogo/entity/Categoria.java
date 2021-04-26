package com.modelagem.software.jogo.mario.world.jogo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idCategoria;

    private String descCat;

    @OneToMany(mappedBy = "categoria")
    private List<Palavra> palavras;

}
