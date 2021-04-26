package com.modelagem.software.jogo.mario.world.jogo.service;

import com.modelagem.software.jogo.mario.world.jogo.dto.DtoPalavra;
import com.modelagem.software.jogo.mario.world.jogo.entity.Categoria;
import com.modelagem.software.jogo.mario.world.jogo.entity.Palavra;
import com.modelagem.software.jogo.mario.world.jogo.entrada.DtoEntradaAtualizarPalavras;
import com.modelagem.software.jogo.mario.world.jogo.repository.PalavraRepository;
import com.modelagem.software.jogo.mario.world.jogo.dto.DtoCategoria;
import com.modelagem.software.jogo.mario.world.jogo.retorno.DtoRetorno;
import com.modelagem.software.jogo.mario.world.jogo.retorno.DtoRetornoConsultarPalavras;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PalavraService {

    @Autowired
    private PalavraRepository repository;

    public DtoRetornoConsultarPalavras consultarPalavras(){

        List<DtoPalavra> retorno = new ArrayList<>();

        List<Palavra> palavras = repository.findAll();

        if(palavras != null && !palavras.isEmpty()){

            retorno = palavras.stream().map( p ->

            {
                return DtoPalavra.builder()
                                 .id(p.getIdPalavra())
                                 .descricao(p.getDescPv())
                                 .categoria(DtoCategoria.builder()
                                                        .id(p.getCategoria().getIdCategoria())
                                                        .descricao(p.getCategoria().getDescCat())
                                                        .build())
                                 .build();

            }).collect(Collectors.toList());

        }

        return DtoRetornoConsultarPalavras.builder()
                                          .palavras(retorno)
                                          .build();

    }


    public DtoRetorno atualizarPalavras(DtoEntradaAtualizarPalavras entrada){

        List<Palavra> palavrasIncluir = new ArrayList<Palavra>();
        List<Palavra> palavrasExcluir = new ArrayList<Palavra>();
        List<Palavra> palavrasAtualizar = new ArrayList<Palavra>();

        List<Palavra> palavrasBase = repository.findAll();

        if(entrada != null && entrada.getPalavras() != null && !entrada.getPalavras().isEmpty()){

            if(palavrasBase != null && !palavrasBase.isEmpty()) {

                // posso atualizar, excluir e incluir

                //incluir:
                palavrasIncluir.addAll(entrada.getPalavras()
                        .stream()
                        .filter( p -> (p.getId() == null) )
                        .map( pNovo -> {
                            return Palavra.builder()
                                          .descPv(pNovo.getDescricao())
                                          .categoria(Categoria.builder()
                                                              .idCategoria(pNovo.getCategoria().getId())
                                                              .descCat(pNovo.getCategoria().getDescricao())
                                                              .build())
                                          .build();
                        }).collect(Collectors.toList()));

                if(!palavrasIncluir.isEmpty()) {

                    entrada.setPalavras(entrada.getPalavras()
                            .stream()
                            .filter( p -> (p.getId() != null) )
                            .collect(Collectors.toList()));

                }


                //excluir:
                List<Integer> idPalavrasBase = palavrasBase.stream().map(p -> p.getIdPalavra()).collect(Collectors.toList());
                List<Integer> idPalavrasEntrada = entrada.getPalavras().stream().map(p -> p.getId()).collect(Collectors.toList());

                for ( Integer idPalavraBase: idPalavrasBase ) {

                    if (!idPalavrasEntrada.contains(idPalavraBase)){
                        palavrasExcluir.add(palavrasBase
                                .stream()
                                .filter(p -> p.getIdPalavra().equals(idPalavraBase))
                                .findFirst().orElse(null));
                    }

                }

                // atualizar somente se tiver alteração:

                // Map com id da palavra e descrição
                Map<Integer, String> idPalavraComDescricaoBase = palavrasBase.stream()
                        .collect(Collectors.toMap(Palavra::getIdPalavra, Palavra::getDescPv));

                // Map com id da palavra e id da categoria
                Map<Integer, Integer> idPalavraComIdCategoriaBase = palavrasBase.stream()
                        .collect(Collectors.toMap(Palavra::getIdPalavra, p -> p.getCategoria().getIdCategoria()));

                for (DtoPalavra palavraEntrada: entrada.getPalavras()) {

                    // se key for igual ao id e descricao for igual a value, NÃO ATUALIZA
                    Map.Entry<Integer, String> palavraSemAlteracaoEmDescricao = idPalavraComDescricaoBase.entrySet()
                            .stream()
                            .filter(map -> map.getKey().equals(palavraEntrada.getId()))
                            .filter(map -> map.getValue().equalsIgnoreCase(palavraEntrada.getDescricao()))
                            .findFirst().orElse(null);

                    if(palavraSemAlteracaoEmDescricao == null) {

                        palavrasAtualizar.add(Palavra.builder()
                                                     .idPalavra(palavraEntrada.getId())
                                                     .descPv(palavraEntrada.getDescricao())
                                                     .categoria(Categoria.builder()
                                                                         .idCategoria(palavraEntrada.getId())
                                                                         .descCat(palavraEntrada.getDescricao())
                                                                         .build())
                                                    .build());

                        break;

                    }

                    // se key for igual ao id e id da categoria for igual a value, NÃO ATUALIZA
                    Map.Entry<Integer, Integer> palavraSemAlteracaoEmCategoria = idPalavraComIdCategoriaBase.entrySet()
                            .stream()
                            .filter(map -> map.getKey().equals(palavraEntrada.getId()))
                            .filter(map -> map.getValue().equals(palavraEntrada.getCategoria().getId()))
                            .findFirst().orElse(null);


                    if(palavraSemAlteracaoEmCategoria == null) {

                        palavrasAtualizar.add(Palavra.builder()
                                                     .idPalavra(palavraEntrada.getId())
                                                     .descPv(palavraEntrada.getDescricao())
                                                     .categoria(Categoria.builder()
                                                                         .idCategoria(palavraEntrada.getCategoria().getId())
                                                                         .descCat(palavraEntrada.getCategoria().getDescricao())
                                                                         .build())
                                                     .build());


                    }
                    
                }

            } else {

                // posso somente incluir

                palavrasIncluir.addAll(entrada.getPalavras()
                        .stream()
                        .map( pNovo -> {
                            return Palavra.builder()
                                          .descPv(pNovo.getDescricao())
                                          .categoria(Categoria.builder()
                                                        .idCategoria(pNovo.getId())
                                                        .descCat(pNovo.getDescricao())
                                                        .build())
                                          .build();
                        }).collect(Collectors.toList()));

            }

            efetuarAtualizacao(palavrasIncluir);
            efetuarAtualizacao(palavrasAtualizar);
            efetuarExclusao(palavrasExcluir);

        } else if (entrada != null && entrada.getPalavras() != null && entrada.getPalavras().isEmpty()){

            // posso somente excluir

            palavrasExcluir.addAll(palavrasBase);
            efetuarExclusao(palavrasExcluir);

        }

        return DtoRetorno.builder().sucesso(true).build();

    }

    private void efetuarAtualizacao(List<Palavra> palavrasIncluir){
        if(!palavrasIncluir.isEmpty()){
            repository.saveAll(palavrasIncluir);
        }
    }

    private void efetuarExclusao(List<Palavra> palavrasExcluir){
        if(!palavrasExcluir.isEmpty()){
            repository.deleteAll(palavrasExcluir);
        }
    }
}
