package br.com.alura.forumhub.domain.respostas.service;

import br.com.alura.forumhub.domain.respostas.*;
import br.com.alura.forumhub.domain.respostas.dto.RespostaCriarDto;
import br.com.alura.forumhub.domain.respostas.dto.RespostaDetalhadaDto;
import br.com.alura.forumhub.domain.respostas.dto.RespostaExposicaoDto;
import br.com.alura.forumhub.domain.topicos.Topico;
import br.com.alura.forumhub.domain.topicos.TopicoRepository;
import br.com.alura.forumhub.domain.usuarios.Usuario;
import br.com.alura.forumhub.domain.usuarios.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class RespostaService {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<RespostaExposicaoDto> getRespostasPorIdTopico(long id) {
        return respostaRepository.getRespostaPorIdTopico(id).stream().map(RespostaExposicaoDto::new).toList();
    }

    @Transactional
    public RespostaDetalhadaDto responderTopico(RespostaCriarDto dadosResposta) {
        Topico topico = topicoRepository.findById(dadosResposta.topicoId()).orElseThrow(() -> new RuntimeException("Topico não existente"));
        Usuario usuario = usuarioRepository.findById(dadosResposta.autorId()).orElseThrow(() -> new RuntimeException("Usuario não existente"));
        LocalDateTime dataCriacao = LocalDateTime.now(ZoneId.of("America/Recife"));
        Resposta resposta = new Resposta(null, dadosResposta.mensagem(), topico, dataCriacao, usuario, false);
        respostaRepository.save(resposta);
        return new RespostaDetalhadaDto(resposta);
    }

    public List<RespostaExposicaoDto> getRespostasPorUsuario(long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new IllegalArgumentException("Usuário não encontrado!");
        }
        return respostaRepository.getRespostaPorIdAutor(usuarioId).stream().map(RespostaExposicaoDto::new).toList();
    }
}
