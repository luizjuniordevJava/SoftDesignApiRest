package br.com.softdesign.rest;

import br.com.softdesign.model.entity.Associado;
import br.com.softdesign.model.repository.AssociadoRepository;
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
 */
/*Anotação para implementar RestController*/
@RestController
/*Anotação para indicar a rota*/
@RequestMapping("/api/associados")
public class AssociadoController {

    private final AssociadoRepository repository;

    /*Anotação para permitir a injeção do repository*/
    @Autowired
    public AssociadoController(AssociadoRepository repository){

        this.repository = repository;
    }

    @GetMapping
    public List<Associado> obterTodos(){
        return repository.findAll();
    }

    /*Anotação para informar que metodo é um post*/
    @PostMapping
    /*Anotação para ter o retorno da requisição para o client*/
    @ResponseStatus(HttpStatus.CREATED)
    public Associado salvar( @RequestBody @Valid Associado associado ){
        /*Anotação RequestBody indica que o objeto JSON será passado no corpo da requisição*/
        return repository.save(associado);
    }

    /*Anotação para pegar o parâmeto passado na url, informar que metodo é um GET*/
    @GetMapping("{id}")
    public Associado acharPorId( @PathVariable Integer id){
        return repository
                .findById(id)
                .orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"cliente não encontrado!"));
                /*Se não encontrar o cliente por id será lançado o erro que objeto não foi encontrado*/
    }

    /*Anotação para pegar o parâmeto passado na url, informar que metodo é um Delete*/
    @DeleteMapping("{id}")
    /*Anotação para ter o retorno 204 sucesso no NO_CONTENT*/
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar( @PathVariable Integer id){
        repository
                .findById(id) /*primeiro busca o associado antes de deletar*/
                .map( associado -> {
                    repository.delete(associado);
                    return Void.TYPE;
                })
                .orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"cliente não encontrado!"));
        /*Se não encontrar o cliente por id será lançado o erro que objeto não foi encontrado*/
    }

    /*Anotação para pegar o parâmeto passado na url, informar que metodo é um Put(update)*/
    @PutMapping("{id}")
    /*Anotação para ter o retorno 204 sucesso no NO_CONTENT*/
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id, @RequestBody @Valid Associado associadoAtualizado){
        repository
                .findById(id) /*primeiro busca o associado antes de deletar*/
                .map( associado -> {
                    associadoAtualizado.setId(associado.getId());
                    return repository.save(associadoAtualizado);
                })
                .orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"cliente não encontrado!"));
        /*Se não encontrar o cliente por id será lançado o erro que objeto não foi encontrado*/
    }

}
