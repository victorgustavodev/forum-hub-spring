package br.com.alura.forumhub.domain.usuarios.dto;

public record DadosPerfilUsuarioDto(
        String nome,
        Long numeroTopicos,
        Long numeroRespostas,
        Long numeroSolucoes
) {
}
