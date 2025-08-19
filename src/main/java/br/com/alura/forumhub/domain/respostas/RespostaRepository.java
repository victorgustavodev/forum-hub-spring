package br.com.alura.forumhub.domain.respostas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Long> {
    List<Resposta> findAllByTopicoId(long id);

    @Query("Select count(r.id) From Resposta r Where r.autor.id = :id")
    Long contarNumeroRespostasPorUsuario(long id);

    @Query("select count(r.id) from Resposta r where r.autor.id = :id and r.solucao = true")
    Long contarNumeroSolucoesPorUsuario(long id);

    @Query("select r from Resposta r where r.topico.id = :id")
    List<Resposta> getRespostaPorIdTopico(long id);

    @Query("select r from Resposta r where r.autor.id = :id")
    List<Resposta> getRespostaPorIdAutor(long id);
}
