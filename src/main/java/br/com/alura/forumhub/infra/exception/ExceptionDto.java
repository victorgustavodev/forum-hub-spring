package br.com.alura.forumhub.infra.exception;

public record ExceptionDto(
        String exception,
        String message
) {
    public ExceptionDto(Exception e) {
        this(
                e.getClass().getSimpleName(),
                e.getLocalizedMessage()
        );
    }
}
