ALTER TABLE usuarios DROP COLUMN perfis,
        ADD COLUMN perfil_id bigint not null;

UPDATE usuarios SET perfil_id = 1 where perfil_id = 0;

ALTER TABLE usuarios ADD CONSTRAINT fk_perfil_id FOREIGN KEY(perfil_id) REFERENCES perfis(id);