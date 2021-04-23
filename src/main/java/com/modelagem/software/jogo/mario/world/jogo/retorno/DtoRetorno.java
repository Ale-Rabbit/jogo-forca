package com.modelagem.software.jogo.mario.world.jogo.retorno;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DtoRetorno implements Serializable {

    private boolean sucesso;

    private String mensagem;

}