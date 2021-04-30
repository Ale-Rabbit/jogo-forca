package com.modelagem.software.jogo.mario.world.jogo.retorno;

import com.modelagem.software.jogo.mario.world.jogo.dto.DtoPalavra;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DtoRetornoConsultarPalavras {

    private List<DtoPalavra> palavras;

}
