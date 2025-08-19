package br.com.alura.forumhub.domain.usuarios.repository;

import br.com.alura.forumhub.domain.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String email);

    @Query("select u.nome From Usuario u where u.id = :id")
    Optional<String> getNome(long id);
}
