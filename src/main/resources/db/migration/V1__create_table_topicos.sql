CREATE TABLE topicos(
    id bigint auto_increment not null,
    titulo varchar(255) not null,
    mensagem text not null,
    status varchar(255) not null,

    primary key(id)
)