package br.com.alura.forumhub.domain.cursos.service;

import br.com.alura.forumhub.domain.cursos.Curso;
import br.com.alura.forumhub.domain.cursos.dto.CursoCriacaoDto;
import br.com.alura.forumhub.domain.cursos.dto.CursoExposicaoDetalhadaDto;
import br.com.alura.forumhub.domain.cursos.dto.CursoExposicaoDto;
import br.com.alura.forumhub.domain.cursos.repository.CursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public CursoExposicaoDto getCurso(long id) {
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi encontrado curso"));
        return new CursoExposicaoDto(curso);
    }

    @Transactional
    public CursoExposicaoDetalhadaDto criarCurso(CursoCriacaoDto dadosCurso) {
        Curso curso = new Curso(null, dadosCurso.nome(), dadosCurso.categoria());
        cursoRepository.save(curso);
        return new CursoExposicaoDetalhadaDto(curso);
    }
}
