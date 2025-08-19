package br.com.alura.forumhub.domain.usuarios.repository;

import br.com.alura.forumhub.domain.usuarios.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
