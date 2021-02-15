package br.com.softdesign.model.repository;

import br.com.softdesign.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Junior
 * @since 15/02/2021
 * @version 1.0
 *
 * Interface criada para extender a classe pai JpaRepository
 * e ter acesso aos metodos do CRUD
 *
 */

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
