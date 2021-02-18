package br.com.softdesign.service;

import br.com.softdesign.exception.PautaNaoEncontradaException;
import br.com.softdesign.model.entity.Pauta;
import br.com.softdesign.model.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Junior
 * @since 18/02/2021
 * @version 1.0
 *
 * Classe criada para implementar as regras de negócio do associado
 *
 */
@Service
@RequiredArgsConstructor
public class PautaService {

    /**
     * Anotação Autowired para permitir a injeção do repository
     */
    @Autowired
    private final PautaRepository repository;

    public List<Pauta> listarTodos(){
        return repository.findAll();
    }

    public Pauta salvar( Pauta pauta ){
        return repository.save(pauta);
    }

    /**
     *  findById primeiro busca a pauta
     *  orElseThrow se não encontrar a pauta por id será lançado o erro que objeto não foi encontrado
     */
    public Pauta acharPorId( Integer id ){
        return repository
                .findById(id)
                .orElseThrow( ()-> new PautaNaoEncontradaException());
    }

    public void deletar( Integer id){
        repository
                .findById(id)
                .map( pauta -> {
                    repository.delete(pauta);
                    return Void.TYPE;
                })
                .orElseThrow( ()-> new PautaNaoEncontradaException());
    }

    public void atualizar( Integer id, Pauta pautaAtualizado){
        repository
                .findById(id)
                .map( pauta -> {
                    pautaAtualizado.setId(pauta.getId());
                    return repository.save(pautaAtualizado);
                })
                .orElseThrow( ()-> new PautaNaoEncontradaException());
    }

}
