package br.com.alura.forumhub.domain.respostas.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RespostaCriarDto(
        @NotNull
        Long topicoId,
        @NotEmpty
        String mensagem,
        @NotNull
        long autorId
) {
}
