package br.com.alura.forumhub.infra.exception;

import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class TratadorDeExcecoes {
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private ResponseEntity<ExceptionDto> tipoDeArgumentoInvalido(MethodArgumentTypeMismatchException exc) {
        return ResponseEntity.badRequest().body(new ExceptionDto(exc));
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    private ResponseEntity<ExceptionDto> httpNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({IllegalArgumentException.class})
    private ResponseEntity<ExceptionDto> argumentoInvalido(IllegalArgumentException exc) {
        return ResponseEntity.badRequest().body(new ExceptionDto(exc));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    private ResponseEntity<ExceptionDto> caminhoNaoEncontrado() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(TokenExpiredException.class)
    private ResponseEntity<ExceptionDto> tokenExpirado(TokenExpiredException exc) {
        return ResponseEntity.badRequest().body(new ExceptionDto(exc));
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ExceptionDto> handleException(RuntimeException ex) {
        return ResponseEntity.internalServerError().body(new ExceptionDto(ex));
    }
}
