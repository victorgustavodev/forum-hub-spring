package br.com.alura.forumhub.domain.respostas.controller;

import br.com.alura.forumhub.domain.respostas.dto.RespostaCriarDto;
import br.com.alura.forumhub.domain.respostas.dto.RespostaDetalhadaDto;
import br.com.alura.forumhub.domain.respostas.dto.RespostaExposicaoDto;
import br.com.alura.forumhub.domain.respostas.service.RespostaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/respostas")
public class RespostaController {

    @Autowired
    private RespostaService service;

    @GetMapping("/topicos/{id}")
    public ResponseEntity<List<RespostaExposicaoDto>> getRepostasPorTopico(@PathVariable long id) {
        List<RespostaExposicaoDto> respostas = service.getRespostasPorIdTopico(id);
        return ResponseEntity.ok(respostas);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<List<RespostaExposicaoDto>> getRespostasPorUsuario(@PathVariable long id) {
        List<RespostaExposicaoDto> respostas = service.getRespostasPorUsuario(id);
        return ResponseEntity.ok(respostas);
    }

    @PostMapping
    public ResponseEntity<?> responderTopico(
            @RequestBody @Valid RespostaCriarDto dadosResposta,
            UriComponentsBuilder uriComponentsBuilder) {
        RespostaDetalhadaDto resposta = service.responderTopico(dadosResposta);
        URI uri = uriComponentsBuilder.path("/respostas/{id}").buildAndExpand(resposta.id()).toUri();
        return ResponseEntity.created(uri).body(resposta);
    }
}
