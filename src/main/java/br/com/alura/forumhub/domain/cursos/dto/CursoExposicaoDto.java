package br.com.alura.forumhub.domain.cursos.dto;

import br.com.alura.forumhub.domain.cursos.CategoriaCurso;
import br.com.alura.forumhub.domain.cursos.Curso;

public record CursoExposicaoDto (
        String nome,
        CategoriaCurso categoria
) {
    public CursoExposicaoDto(Curso curso){
        this(curso.getNome(), curso.getCategoria());
    }
}
