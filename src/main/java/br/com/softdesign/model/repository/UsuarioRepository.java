package br.com.softdesign.model.repository;

import br.com.softdesign.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Junior
 * @since 16/02/2021
 * @version 1.0
 *
 * Interface criada para extender a classe pai JpaRepository
 * e ter acesso aos metodos do CRUD
 *
 */

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByUsername(String username);

    boolean existsByUsername(String username);
}
