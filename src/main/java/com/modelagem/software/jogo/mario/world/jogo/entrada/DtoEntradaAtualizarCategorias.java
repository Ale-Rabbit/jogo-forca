package com.modelagem.software.jogo.mario.world.jogo.entrada;

import com.modelagem.software.jogo.mario.world.jogo.dto.DtoCategoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DtoEntradaAtualizarCategorias {

    private List<DtoCategoria> categorias;

}
