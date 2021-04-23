package com.modelagem.software.jogo.mario.world.jogo.retorno;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DtoRetornoConsultarCategorias {

    private List<DtoCategoria> categorias;

}
