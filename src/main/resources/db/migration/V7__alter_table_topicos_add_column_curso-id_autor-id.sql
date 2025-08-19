ALTER TABLE topicos
        ADD COLUMN usuario_id bigint,
        ADD COLUMN curso_id bigint,
        ADD CONSTRAINT fk_usuario FOREIGN KEY(usuario_id) REFERENCES usuarios(id),
        ADD CONSTRAINT fk_curso FOREIGN KEY(curso_id) REFERENCES cursos(id);

UPDATE topicos SET usuario_id = 1 WHERE usuario_id is null;
UPDATE topicos SET curso_id = 1 WHERE curso_id is null;