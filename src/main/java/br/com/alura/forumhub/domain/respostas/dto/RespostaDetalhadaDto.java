package br.com.alura.forumhub.domain.respostas.dto;

import br.com.alura.forumhub.domain.respostas.Resposta;
import br.com.alura.forumhub.domain.usuarios.dto.UsuarioExposicaoDto;

import java.time.LocalDateTime;

public record RespostaDetalhadaDto(
        Long id,
        String mensagem,
        UsuarioExposicaoDto autor,
        LocalDateTime dataCriacao,
        boolean solucao
) {
    public RespostaDetalhadaDto(Resposta resposta) {
        this(resposta.getId(), resposta.getMensagem(), new UsuarioExposicaoDto(resposta.getAutor()), resposta.getDataCriacao(), resposta.isSolucao());
    }
}
