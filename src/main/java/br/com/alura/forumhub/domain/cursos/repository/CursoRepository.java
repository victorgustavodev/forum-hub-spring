package br.com.alura.forumhub.domain.cursos.repository;

import br.com.alura.forumhub.domain.cursos.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
}
