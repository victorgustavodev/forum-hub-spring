package br.com.alura.forumhub.domain.usuarios.autenticacao;

import br.com.alura.forumhub.domain.usuarios.Usuario;
import br.com.alura.forumhub.domain.usuarios.autenticacao.dto.AutenticacaoDto;
import br.com.alura.forumhub.domain.usuarios.autenticacao.dto.RegistroDto;
import br.com.alura.forumhub.domain.usuarios.repository.PerfilRepository;
import br.com.alura.forumhub.domain.usuarios.repository.UsuarioRepository;
import br.com.alura.forumhub.infra.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PerfilRepository perfilRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid AutenticacaoDto dados) {
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var autenticacao = authenticationManager.authenticate(token);
        return ResponseEntity.ok(tokenService.gerarToken((Usuario) autenticacao.getPrincipal()));
    }

    @PostMapping("/register")
    @Transactional
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<?> registrarUsuario(@RequestBody @Valid RegistroDto registroDto) {
        Usuario usuario = new Usuario();
        usuario.setNome(registroDto.nome());
        usuario.setEmail(registroDto.email());
        usuario.setSenha(passwordEncoder.encode(registroDto.senha()));
        usuario.setPerfil(perfilRepository.findById(registroDto.idPerfil()).orElseThrow());
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }
}
