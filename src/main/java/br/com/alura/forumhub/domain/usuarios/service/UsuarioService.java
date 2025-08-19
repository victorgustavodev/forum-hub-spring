package br.com.alura.forumhub.domain.usuarios.service;

import br.com.alura.forumhub.domain.respostas.RespostaRepository;
import br.com.alura.forumhub.domain.topicos.TopicoRepository;
import br.com.alura.forumhub.domain.usuarios.dto.DadosPerfilUsuarioDto;
import br.com.alura.forumhub.domain.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RespostaRepository repostasRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    public DadosPerfilUsuarioDto getDadosUsuario(long id) {
        String nome = getNome(id);
        long totalTopicos =totalTopicos(id);
        long totalRespotas = totalRespotas(id);
        long totalSolucoes = totalSolucoes(id);
        return new DadosPerfilUsuarioDto(nome, totalTopicos, totalRespotas, totalSolucoes);
    }

    private String getNome(long id) {
        return usuarioRepository.getNome(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }

    private Long totalTopicos(long id) {
        return topicoRepository.contarNumeroTopicosPorUsuario(id);
    }

    private Long totalRespotas(long id) {
        return repostasRepository.contarNumeroRespostasPorUsuario(id);
    }

    private Long totalSolucoes(long id) {
        return repostasRepository.contarNumeroSolucoesPorUsuario(id);
    }
}
