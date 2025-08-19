package br.com.alura.forumhub.infra.security;

import br.com.alura.forumhub.domain.usuarios.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

public class FiltroDeSeguranca extends OncePerRequestFilter {

    private HandlerExceptionResolver handlerExceptionResolver;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    public FiltroDeSeguranca(HandlerExceptionResolver handlerExceptionResolver) {
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            var token = coletarTokenJwt(request);
            if (token == null) {
                filterChain.doFilter(request, response);
                return;
            }
            var subject = tokenService.getSubject(token);
            UserDetails usuario = usuarioRepository.findByEmail(subject);
            var autenticacao = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(autenticacao);
            filterChain.doFilter(request, response);
        }
        catch (Exception exc) {
            handlerExceptionResolver.resolveException(request, response, null, exc);
        }
    }

    private String coletarTokenJwt(HttpServletRequest request) {
        String jwtToken = request.getHeader("authorization");
        if (jwtToken != null) {
            return jwtToken.replace("Bearer ", "");
        }

        return null;
    }
}
