package br.com.alura.forumhub.domain.topicos.services;

import br.com.alura.forumhub.domain.cursos.Curso;
import br.com.alura.forumhub.domain.cursos.repository.CursoRepository;
import br.com.alura.forumhub.domain.respostas.Resposta;
import br.com.alura.forumhub.domain.topicos.TopicoRepository;
import br.com.alura.forumhub.domain.topicos.dto.TopicoAtualizacaoDto;
import br.com.alura.forumhub.domain.topicos.dto.TopicoCadastroDto;
import br.com.alura.forumhub.domain.topicos.dto.TopicoExposicaoDto;
import br.com.alura.forumhub.domain.respostas.RespostaRepository;
import br.com.alura.forumhub.domain.topicos.StatusTopico;
import br.com.alura.forumhub.domain.topicos.Topico;
import br.com.alura.forumhub.domain.topicos.dto.TopicoExposicaoDetalhadaDto;
import br.com.alura.forumhub.domain.usuarios.Usuario;
import br.com.alura.forumhub.domain.usuarios.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopicoService {

    @Autowired
    TopicoRepository topicoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    RespostaRepository respostaRepository;

    @Transactional
    public TopicoExposicaoDto cadastrarTopico(TopicoCadastroDto dados) {
        if (topicoRepository.existsByTitulo(dados.titulo())) {
            throw new IllegalArgumentException("Não pode haver mais de um tópico com o mesmo título");
        }
        if (topicoRepository.existsByMensagem(dados.mensagem())) {
            throw new IllegalArgumentException("Não pode haver mais du um tópico com a mesma mensagem");
        }
        Usuario autor = usuarioRepository.findById(dados.idAutor()).orElseThrow();
        Curso curso = cursoRepository.findById(dados.idCurso()).orElseThrow();
        Topico topico = new Topico(
                dados.titulo(),
                dados.mensagem(),
                LocalDateTime.now(),
                StatusTopico.ABERTO,
                autor,
                curso
        );
        topicoRepository.save(topico);
        return new TopicoExposicaoDto(topico);
    }

    public Page<TopicoExposicaoDto> listarTodosOsTopicos(Pageable pageable) {
        return topicoRepository.findAll(pageable).map(TopicoExposicaoDto::new);
    }

    public TopicoExposicaoDetalhadaDto detalharTopicoPorId(long id) {
        Topico topico = topicoRepository.findById(id).orElseThrow();
        List<Resposta> respostas = respostaRepository.findAllByTopicoId(id);
        topico.setRespostas(respostas);
        return new TopicoExposicaoDetalhadaDto(topico);
    }

    @Transactional
    public TopicoExposicaoDto alterarTopico(long id, TopicoAtualizacaoDto dados) {
        if (dados.titulo() == null && dados.mensagem() == null && dados.status() == null) {
            throw new RuntimeException("O corpo da alteração não pode ser vazio");
        }
        Topico topico = topicoRepository.findById(id).orElseThrow();
        if (dados.titulo() != null) {
            topico.setTitulo(dados.titulo());
        }
        if (dados.mensagem() != null) {
            topico.setMensagem(dados.mensagem());
        }
        if (dados.status() != null) {
            topico.setStatusTopico(dados.status());
        }
        return new TopicoExposicaoDto(topico);
    }

    @Transactional
    public void deletarTopico(long id) {
        topicoRepository.deleteById(id);
    }
}
