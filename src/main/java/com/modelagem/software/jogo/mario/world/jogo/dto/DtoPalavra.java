package com.modelagem.software.jogo.mario.world.jogo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DtoPalavra {

    private Integer id;

    private String descricao;

    private DtoCategoria categoria;

}
