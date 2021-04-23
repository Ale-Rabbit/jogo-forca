package com.modelagem.software.jogo.mario.world.jogo.service;

import com.modelagem.software.jogo.mario.world.jogo.entity.Categoria;
import com.modelagem.software.jogo.mario.world.jogo.entrada.DtoEntradaConsultarCategorias;
import com.modelagem.software.jogo.mario.world.jogo.repository.CategoriaRepository;
import com.modelagem.software.jogo.mario.world.jogo.retorno.DtoCategoria;
import com.modelagem.software.jogo.mario.world.jogo.retorno.DtoRetorno;
import com.modelagem.software.jogo.mario.world.jogo.retorno.DtoRetornoConsultarCategorias;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public DtoRetornoConsultarCategorias consultarCategorias(){

        List<DtoCategoria> retorno = new ArrayList<>();

        List<Categoria> categorias = repository.findAll();


        if (categorias != null && !categorias.isEmpty()){

            retorno = categorias.stream().map( c ->
            {

                DtoCategoria retornoCategoria = DtoCategoria.builder()
                                                            .id(c.getIdCategoria())
                                                            .descricao(c.getDescCat())
                                                            .build();

                return retornoCategoria;

            }).collect(Collectors.toList());

        }

        return DtoRetornoConsultarCategorias.builder()
                                            .categorias(retorno)
                                            .build();

    }

    public DtoRetorno atualizarCategorias(DtoEntradaConsultarCategorias entrada){

        List<Categoria> categoriasIncluir = new ArrayList<Categoria>();
        List<Categoria> categoriasExcluir = new ArrayList<Categoria>();
        List<Categoria> categoriasAtualizar = new ArrayList<Categoria>();

        List<Categoria> categoriasDaBase = repository.findAll();

        if(entrada != null && entrada.getCategorias() != null && !entrada.getCategorias().isEmpty()){

            if(categoriasDaBase != null && !categoriasDaBase.isEmpty()){

                // posso atualizar, excluir e incluir

               //incluir:
               categoriasIncluir.addAll(entrada.getCategorias()
                        .stream()
                        .filter( c -> (c.getId() == null))
                        .map( cNovo -> {
                            return Categoria.builder().descCat(cNovo.getDescricao()).build();
                        }).collect(Collectors.toList()));

               if(!categoriasIncluir.isEmpty()){

                   entrada.setCategorias(entrada.getCategorias()
                           .stream()
                           .filter(c -> (c.getId() != null)).collect(Collectors.toList()));

               }


                //excluir:
                List<Integer> idCategoriasBase = categoriasDaBase.stream().map(c -> c.getIdCategoria()).collect(Collectors.toList());
                List<Integer> idCategoriasEntrada = entrada.getCategorias().stream().map( c-> c.getId()).collect(Collectors.toList());

                for ( Integer idCategoriaBase: idCategoriasBase ) {

                    if (!idCategoriasEntrada.contains(idCategoriaBase)){
                        categoriasExcluir.add(categoriasDaBase
                                              .stream()
                                              .filter(c -> c.getIdCategoria().equals(idCategoriaBase))
                                              .findFirst().orElse(null));
                    }

                }

                // atualizar
                categoriasAtualizar.addAll(entrada.getCategorias()
                        .stream()
                        .map( cNovo -> {
                            return Categoria.builder()
                                    .idCategoria(cNovo.getId()).descCat(cNovo.getDescricao())
                                    .build();
                        }).collect(Collectors.toList()));


            } else {

                // posso somente incluir

                categoriasIncluir.addAll(entrada.getCategorias()
                        .stream()
                        .map( cNovo -> {
                            return Categoria.builder().descCat(cNovo.getDescricao()).build();
                        }).collect(Collectors.toList()));

            }

            efetuarAtualizacao(categoriasIncluir);
            efetuarAtualizacao(categoriasAtualizar);
            efetuarExclusao(categoriasExcluir);


        } else if (entrada != null && entrada.getCategorias() != null && entrada.getCategorias().isEmpty()){

            // posso somente excluir

            categoriasExcluir.addAll(categoriasDaBase);
            efetuarExclusao(categoriasExcluir);

        }

        return DtoRetorno.builder().sucesso(true).build();

    }

    private void efetuarAtualizacao(List<Categoria> categoriasIncluir){
        if(!categoriasIncluir.isEmpty()){
            repository.saveAll(categoriasIncluir);
        }
    }

    private void efetuarExclusao(List<Categoria> categoriasExcluir){
        if(!categoriasExcluir.isEmpty()){
            repository.deleteAll(categoriasExcluir);
        }
    }

}


