package br.com.alura.forumhub.domain.topicos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTitulo(String titulo);
    boolean existsByMensagem(String mensagem);

    @Query("select count(t.id) from Topico t where t.autor.id = :id")
    long contarNumeroTopicosPorUsuario(long id);
}
