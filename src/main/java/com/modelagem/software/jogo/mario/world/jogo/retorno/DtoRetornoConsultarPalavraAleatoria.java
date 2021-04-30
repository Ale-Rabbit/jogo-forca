package com.modelagem.software.jogo.mario.world.jogo.retorno;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
public class DtoRetornoConsultarPalavraAleatoria extends DtoRetorno {

    private String palavra;

    public DtoRetornoConsultarPalavraAleatoria(boolean sucesso, String mensagem) {
        super(sucesso, mensagem);
    }

    @Builder(builderMethodName = "builderDefault")
    public DtoRetornoConsultarPalavraAleatoria(String palavra) {
        this.palavra = palavra;
        this.setSucesso(true);
    }


}
