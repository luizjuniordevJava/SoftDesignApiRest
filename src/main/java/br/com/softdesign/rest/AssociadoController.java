package br.com.softdesign.rest;

import br.com.softdesign.exception.AssociadoNaoEncontadoException;
import br.com.softdesign.model.entity.Associado;
import br.com.softdesign.model.repository.AssociadoRepository;
import br.com.softdesign.service.AssociadoService;
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
     * Anotação Autowired para permitir a injeção do AssociadoService
     */
    @Autowired
    private final AssociadoRepository repository;
    /**
     * Anotação Autowired para permitir a injeção do AssociadoService
     */
    @Autowired
    private final AssociadoService service;

    @GetMapping
    public List<Associado> obterTodos(){
        return service.listarTodos();
    }

    /**
     *  Anotação PostMapping para informar que metodo é um post
     *  Anotação ResponseStatus(HttpStatus.CREATED) retorno de msg pro client
     *  Anotação RequestBody indica que o objeto JSON será passado no corpo da requisição
     *  Anotação Valid para validar o objeto associado
     *
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Associado salvar( @RequestBody @Valid Associado associado ){
        try {
          return  service.salvar( associado );

        }catch ( AssociadoNaoEncontadoException e){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, e.getMessage() );
        }

    }

    /**
     *  Anotação GetMapping para pegar o parâmeto passado na url
     */
    @GetMapping("{id}")
    public Associado acharPorId( @PathVariable Integer id){
        try {
            return service.acharPorId(id);

        }catch ( AssociadoNaoEncontadoException e ){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, e.getMessage() );
        }
    }

    /**
     *  Anotação DeleteMapping para pegar o parâmeto passado na url, informar que metodo é um Delete
     *  Anotação ResponseStatus(HttpStatus.NO_CONTENT) para ter o retorno 204 sucesso no NO_CONTENT
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar( @PathVariable Integer id){
        try {
            service.deletar(id);
        }catch ( AssociadoNaoEncontadoException e ){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, e.getMessage() );
        }
    }

    /**
     *  Anotação PutMapping para pegar o parâmeto passado na url, informar que metodo é um Put(update)
     *  Anotação ResponseStatus(HttpStatus.NO_CONTENT) para ter o retorno 204 sucesso no NO_CONTENT
     */
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id, @RequestBody @Valid Associado associadoAtualizado){
        try {
            service.atualizar(id, associadoAtualizado);
        }catch ( AssociadoNaoEncontadoException e ){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, e.getMessage() );
        }

    }

}
