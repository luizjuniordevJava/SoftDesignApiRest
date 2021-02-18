package br.com.softdesign.service;

import br.com.softdesign.exception.AssociadoNaoEncontadoException;
import br.com.softdesign.exception.CPFCadastradoException;
import br.com.softdesign.model.entity.Associado;
import br.com.softdesign.model.repository.AssociadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Junior
 * @since 17/02/2021
 * @version 1.0
 *
 * Classe criada para implementar as regras de negócio do associado
 *
 */
@Service
@RequiredArgsConstructor
public class AssociadoService {

    /**
     * Anotação Autowired para permitir a injeção do repository
     */
    @Autowired
    private final AssociadoRepository repository;

    public List<Associado> listarTodos(){
        return repository.findAll();
    }

    public Associado salvar(Associado associado ){
        boolean exists = repository.existsByCPF(associado.getCpf());
        if ( exists ){
            throw new CPFCadastradoException();
        }
        return repository.save( associado );
    }

    /**
     *  findById primeiro busca o associado
     *  orElseThrow se não encontrar o associado por id será lançado a exception
     */

    public Associado acharPorId( Integer id ){
        return repository
                .findById(id)
                .orElseThrow( ()-> new AssociadoNaoEncontadoException());

    }

    public void atualizar( Integer id, Associado associadoAtualizado ){
        repository
                .findById( id )
                .map( associado -> {
                    associadoAtualizado.setId(associado.getId());
                    return repository.save(associadoAtualizado);
                })
                .orElseThrow( ()-> new AssociadoNaoEncontadoException());
    }

    public void deletar( Integer id ){
        repository
                .findById( id )
                .map( associado -> {
                    repository.delete(associado);
                    return Void.TYPE;
                })
                .orElseThrow( ()-> new AssociadoNaoEncontadoException());
    }

}
