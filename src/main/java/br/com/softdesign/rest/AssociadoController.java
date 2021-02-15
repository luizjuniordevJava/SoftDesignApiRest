package br.com.softdesign.rest;

import br.com.softdesign.model.entity.Associado;
import br.com.softdesign.model.repository.AssociadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Junior
 * @since 10/02/2021
 * @version 1.0
 *
 * Classe criada para implementar a controller do Associado
 *
 * Anotação RestController para implementar RestController
 * Anotação RequiredArgsConstructor lombok para gerar o construtor como final em tempo de execução
 * Anotação RequestMapping para indicar a rota
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/associados")
public class AssociadoController {

    /**
     * Anotação Autowired para permitir a injeção do repository
     */
    @Autowired
    private final AssociadoRepository repository;

    @GetMapping
    public List<Associado> obterTodos(){
        return repository.findAll();
    }

    /**
     *  Anotação para informar que metodo é um post
     *  Anotação para ter o retorno da requisição para o client
     *  Anotação RequestBody indica que o objeto JSON será passado no corpo da requisição
     *  Anotação Valid para validar o objeto associado
     *  Anotação ResponseStatus(HttpStatus.CREATED) retorno de msg pro client
     *
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Associado salvar( @RequestBody @Valid Associado associado ){
        return repository.save(associado);
    }

    /**
     *  Anotação GetMapping para pegar o parâmeto passado na url
     *  findById primeiro busca o associado
     *  orElseThrow se não encontrar o cliente por id será lançado o erro que objeto não foi encontrado
     */
    @GetMapping("{id}")
    public Associado acharPorId( @PathVariable Integer id){
        return repository
                .findById(id)
                .orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"associado não encontrado!"));

    }

    /**
     *  Anotação DeleteMapping para pegar o parâmeto passado na url, informar que metodo é um Delete
     *  Anotação ResponseStatus(HttpStatus.NO_CONTENT) para ter o retorno 204 sucesso no NO_CONTENT
     *  findById primeiro busca o associado
     *  orElseThrow se não encontrar o cliente por id será lançado o erro que objeto não foi encontrado
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar( @PathVariable Integer id){
        repository
                .findById(id)
                .map( associado -> {
                    repository.delete(associado);
                    return Void.TYPE;
                })
                .orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"associado não encontrado!"));
    }

    /**
     *  Anotação PutMapping para pegar o parâmeto passado na url, informar que metodo é um Put(update)
     *  Anotação ResponseStatus(HttpStatus.NO_CONTENT) para ter o retorno 204 sucesso no NO_CONTENT
     *  findById primeiro busca o associado
     *  orElseThrow se não encontrar o cliente por id será lançado o erro que objeto não foi encontrado
     */
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id, @RequestBody @Valid Associado associadoAtualizado){
        repository
                .findById(id)
                .map( associado -> {
                    associadoAtualizado.setId(associado.getId());
                    return repository.save(associadoAtualizado);
                })
                .orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"associado não encontrado!"));
    }

}
