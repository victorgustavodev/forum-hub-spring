package br.com.alura.forumhub.domain.cursos.dto;

import br.com.alura.forumhub.domain.cursos.CategoriaCurso;
import br.com.alura.forumhub.domain.cursos.Curso;

public record CursoExposicaoDetalhadaDto(
        Long id,
        String nome,
        CategoriaCurso categoria
) {
    public CursoExposicaoDetalhadaDto(Curso curso){
        this(curso.getId(), curso.getNome(), curso.getCategoria());
    }
}
