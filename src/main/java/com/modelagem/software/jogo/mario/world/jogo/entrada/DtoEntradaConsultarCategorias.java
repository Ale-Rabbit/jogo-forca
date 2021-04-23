package com.modelagem.software.jogo.mario.world.jogo.entrada;

import com.modelagem.software.jogo.mario.world.jogo.retorno.DtoCategoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DtoEntradaConsultarCategorias {

    private List<DtoCategoria> categorias;

}
