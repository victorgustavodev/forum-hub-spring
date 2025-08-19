package br.com.alura.forumhub.domain.cursos;

import br.com.alura.forumhub.domain.cursos.dto.CursoCriacaoDto;
import br.com.alura.forumhub.domain.cursos.dto.CursoExposicaoDetalhadaDto;
import br.com.alura.forumhub.domain.cursos.dto.CursoExposicaoDto;
import br.com.alura.forumhub.domain.cursos.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping("/{id}")
    public ResponseEntity<CursoExposicaoDto> getCurso(@PathVariable long id) {
        CursoExposicaoDto curso = cursoService.getCurso(id);
        return ResponseEntity.ok(curso);
    }

    @PostMapping
    public ResponseEntity<?> criarCurso(@Valid @RequestBody CursoCriacaoDto dadosCurso, UriComponentsBuilder uriComponentsBuilder) {
        CursoExposicaoDetalhadaDto curso = cursoService.criarCurso(dadosCurso);
        URI uri = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.id()).toUri();
        return ResponseEntity.created(uri).body(curso);
    }
}
