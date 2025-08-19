package br.com.alura.forumhub.domain.topicos;

import br.com.alura.forumhub.domain.cursos.Curso;
import br.com.alura.forumhub.domain.respostas.Resposta;
import br.com.alura.forumhub.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "topicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusTopico statusTopico;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario autor;
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
    @OneToMany
    @JoinColumn(name = "topico_id")
    private List<Resposta> respostas;

    public Topico(
            String titulo,
            String mensagem,
            LocalDateTime dataCriacao,
            StatusTopico status,
            Usuario autor,
            Curso curso) {
        this.id = null;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.dataCriacao = dataCriacao;
        this.statusTopico = status;
        this.autor = autor;
        this.curso = curso;
    }

}
