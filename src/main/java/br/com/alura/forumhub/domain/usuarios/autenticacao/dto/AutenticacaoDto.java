package br.com.alura.forumhub.domain.usuarios.autenticacao.dto;

import jakarta.validation.constraints.Email;

public record AutenticacaoDto(
        @Email
        String login,
        String senha
) {
}
