package br.com.softdesign.model.repository;

import br.com.softdesign.model.entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PautaRepository extends JpaRepository<Pauta,Integer> {
}
