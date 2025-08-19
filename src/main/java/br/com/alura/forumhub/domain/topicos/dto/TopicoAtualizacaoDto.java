package br.com.alura.forumhub.domain.topicos.dto;

import br.com.alura.forumhub.domain.topicos.StatusTopico;

public record TopicoAtualizacaoDto(
        String titulo,
        String mensagem,
        StatusTopico status
) {
}
