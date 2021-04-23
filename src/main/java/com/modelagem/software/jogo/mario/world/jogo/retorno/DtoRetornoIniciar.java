package com.modelagem.software.jogo.mario.world.jogo.retorno;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
public class DtoRetornoIniciar extends DtoRetorno {

    private String palavra;

    public DtoRetornoIniciar(boolean sucesso, String mensagem) {
        super(sucesso, mensagem);
    }

    @Builder(builderMethodName = "builderDefault")
    public DtoRetornoIniciar(String palavra) {
        this.palavra = palavra;
        this.setSucesso(true);
    }


}
