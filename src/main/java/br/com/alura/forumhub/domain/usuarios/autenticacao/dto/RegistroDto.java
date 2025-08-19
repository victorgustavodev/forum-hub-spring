package br.com.alura.forumhub.domain.usuarios.autenticacao.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegistroDto(
        @NotEmpty
        String nome,
        @NotEmpty
        @Email
        String email,
        @NotEmpty
        String senha,
        @NotNull
        long idPerfil
) {
}
