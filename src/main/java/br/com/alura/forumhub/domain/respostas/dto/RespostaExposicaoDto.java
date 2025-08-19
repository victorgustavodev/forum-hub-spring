package br.com.alura.forumhub.domain.respostas.dto;

import br.com.alura.forumhub.domain.respostas.Resposta;
import br.com.alura.forumhub.domain.usuarios.dto.UsuarioExposicaoDto;

import java.time.LocalDateTime;

public record RespostaExposicaoDto(
        String mensagem,
        UsuarioExposicaoDto autor,
        LocalDateTime dataCriacao,
        boolean solucao
) {

    public RespostaExposicaoDto(Resposta resposta) {
        this(resposta.getMensagem(), new UsuarioExposicaoDto(resposta.getAutor()), resposta.getDataCriacao(), resposta.isSolucao());
    }
}
