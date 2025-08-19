package br.com.alura.forumhub.domain.topicos.controllers;

import br.com.alura.forumhub.domain.topicos.dto.TopicoCadastroDto;
import br.com.alura.forumhub.domain.topicos.dto.TopicoExposicaoDto;
import br.com.alura.forumhub.domain.topicos.dto.TopicoAtualizacaoDto;
import br.com.alura.forumhub.domain.topicos.dto.TopicoExposicaoDetalhadaDto;
import br.com.alura.forumhub.domain.topicos.services.TopicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    TopicoService service;

    @GetMapping
    public ResponseEntity<Page<TopicoExposicaoDto>> getTopicos(@PageableDefault(size = 10, sort = {"titulo"}, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<TopicoExposicaoDto> topicos = service.listarTodosOsTopicos(pageable);
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoExposicaoDetalhadaDto> detalharTopico(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(service.detalharTopicoPorId(id));
    }

    @PostMapping
    public ResponseEntity<TopicoExposicaoDto> criarTopico(
            @RequestBody @Valid TopicoCadastroDto dadosCadastroTopico,
            UriComponentsBuilder uriComponentsBuilder) {

        TopicoExposicaoDto topicoExposicaoDto = service.cadastrarTopico(dadosCadastroTopico);

        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topicoExposicaoDto.id()).toUri();
        return ResponseEntity.created(uri).body(topicoExposicaoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoExposicaoDto> alterarTopico(@PathVariable long id, @RequestBody TopicoAtualizacaoDto dados) {
        return ResponseEntity.ok(service.alterarTopico(id, dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarTopico(@PathVariable long id) {
        service.deletarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
