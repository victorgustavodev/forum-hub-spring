CREATE TABLE respostas (
    id bigint not null auto_increment,
    mensagem text not null,
    topico_id bigint not null,
    data_criacao datetime not null default CURRENT_TIMESTAMP,
    usuario_id bigint not null,
    solucao tinyint not null default 0,

    primary key(id),
    CONSTRAINT fk_topicos FOREIGN KEY (topico_id) REFERENCES topicos(id),
    CONSTRAINT fk_usuarios FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
)