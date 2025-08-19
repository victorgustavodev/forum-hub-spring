package br.com.alura.forumhub.domain.topicos.dto;

import br.com.alura.forumhub.domain.cursos.dto.CursoExposicaoDto;
import br.com.alura.forumhub.domain.usuarios.dto.UsuarioExposicaoDto;
import br.com.alura.forumhub.domain.topicos.StatusTopico;
import br.com.alura.forumhub.domain.topicos.Topico;

import java.time.LocalDateTime;

public record TopicoExposicaoDto(
        long id,
        String titulo,
        StatusTopico status,
        LocalDateTime dataCriacao,
        String mensagem,
        UsuarioExposicaoDto autor,
        CursoExposicaoDto curso
) {
    public TopicoExposicaoDto(Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getStatusTopico(),
                topico.getDataCriacao(),
                topico.getMensagem(),
                new UsuarioExposicaoDto(topico.getAutor()),
                new CursoExposicaoDto(topico.getCurso()));
    }
}
