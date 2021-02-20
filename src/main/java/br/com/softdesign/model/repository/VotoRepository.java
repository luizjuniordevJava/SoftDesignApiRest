package br.com.softdesign.model.repository;

import br.com.softdesign.model.entity.Voto;
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
public interface VotoRepository extends JpaRepository<Voto, Integer> {
    @Query(value = "select case when count(v)> 0 then true else false end from Voto v " +
                    "where cast(v.id_pauta as varchar) = :id_pauta and cast(v.id_associado as varchar) = :id_associado", nativeQuery = true)
    boolean existsVoteByPautaUser( @Param("id_pauta") String id_pauta,  @Param("id_associado") String id_associado);
}
