package br.com.softdesign.service;

import br.com.softdesign.exception.AssociadoNaoEncontadoException;
import br.com.softdesign.exception.CPFCadastradoException;
import br.com.softdesign.model.entity.Associado;
import br.com.softdesign.model.entity.Usuario;
import br.com.softdesign.model.repository.AssociadoRepository;
import br.com.softdesign.service.dto.AssociadoDTO;
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
     * Anotação Autowired para permitir a injeção do repository e service
     */
    @Autowired
    private final AssociadoRepository repository;

    @Autowired
    private final UsuarioService usuarioService;

    public List<Associado> listarTodos(){
        return repository.findAll();
    }

    public Associado salvar( AssociadoDTO associadoDTO ){
        boolean exists = repository.existsByCPF(associadoDTO.getCpf());
        if ( exists ){
            throw new CPFCadastradoException();
        }else {
            Usuario usuario = usuarioService.loadByUsername(associadoDTO.getUsuario());
            Associado associado = new Associado();
            associado.setNome( associadoDTO.getNome() );
            associado.setCpf( associadoDTO.getCpf() );
            associado.setUsuario( usuario );

            return repository.save( associado );
        }

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
