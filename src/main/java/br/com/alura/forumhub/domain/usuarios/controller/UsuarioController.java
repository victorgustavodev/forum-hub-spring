package br.com.alura.forumhub.domain.usuarios.controller;

import br.com.alura.forumhub.domain.usuarios.dto.DadosPerfilUsuarioDto;
import br.com.alura.forumhub.domain.usuarios.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/{id}")
    public ResponseEntity<DadosPerfilUsuarioDto> dadosUsuario(@PathVariable long id) {
        DadosPerfilUsuarioDto dadosUsuario = service.getDadosUsuario(id);
        return ResponseEntity.ok(dadosUsuario);
    }

}
