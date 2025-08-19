package br.com.alura.forumhub.domain.cursos.dto;

import br.com.alura.forumhub.domain.cursos.CategoriaCurso;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CursoCriacaoDto(
        @NotEmpty
        String nome,
        @NotNull
        CategoriaCurso categoria
) {
}
