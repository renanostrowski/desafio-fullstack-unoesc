package br.edu.unoesc.desafiofullstackunoesc.reposository;

import br.edu.unoesc.desafiofullstackunoesc.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface iUsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findAll();

    @Query("select u from Usuario u where u.email = :email")
    Usuario buscarPorEmail(String email);
}
