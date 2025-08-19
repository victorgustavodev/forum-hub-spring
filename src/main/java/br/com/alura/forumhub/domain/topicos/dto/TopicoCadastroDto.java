package br.com.alura.forumhub.domain.topicos.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record TopicoCadastroDto (
        Long id,
        @NotEmpty
        String titulo,
        @NotEmpty
        String mensagem,
        @NotNull
        @JsonAlias({"autor", "autor_id"})
        Long idAutor,
        @NotNull
        @JsonAlias({"curso", "curso_id"})
        Long idCurso
) {
}
