package br.com.softdesign.model.repository;

import br.com.softdesign.model.entity.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Junior
 * @since 10/02/2021
 * @version 1.0
 *
 * Interface criada para extender a classe pai JpaRepository
 * e ter acesso aos metodos do CRUD
 *
 */
public interface AssociadoRepository extends JpaRepository<Associado,Integer> {

    @Query("select case when count(c)> 0 then true else false end from Associado c where lower(c.cpf) like lower(:cpf)")
    boolean existsByCPF(@Param("cpf") String cpf);
}
