package br.com.softdesign.rest;

import br.com.softdesign.exception.PautaNaoEncontradaException;
import br.com.softdesign.model.entity.Pauta;
import br.com.softdesign.service.PautaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Junior
 * @since 13/02/2021
 * @version 1.0
 *
 * Classe criada para implementar a controller das Pautas
 *
 * Anotação para implementar RestController
 * Anotação RequiredArgsConstructor lombok para gerar o construtor como final em tempo de execução
 * Anotação RequestMapping para indicar a rota
 *
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pautas")
public class PautaController {

    /**
     * Anotação Autowired para permitir a injeção do repository
     */
    @Autowired
    private final PautaService service;

    @GetMapping
    public List<Pauta> obterTodos(){
        return service.listarTodos();
    }

    /**
     *  Anotação PostMapping para informar que metodo é um post
     *  Anotação ResponseStatus(HttpStatus.CREATED) retorno de msg pro client
     *  Anotação RequestBody indica que o objeto JSON será passado no corpo da requisição
     *  Anotação Valid para validar o objeto pauta
     *
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pauta salvar( @RequestBody @Valid Pauta pauta ){
        try {
            return service.salvar( pauta );
        }catch (PautaNaoEncontradaException e){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, e.getMessage() );
        }

    }

    /**
     *  Anotação GetMapping para pegar o parâmeto passado na url
     */
    @GetMapping("{id}")
    public Pauta acharPorId( @PathVariable Integer id){
        try {
            return service.acharPorId(id);
        }catch (PautaNaoEncontradaException e){
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
        }catch (PautaNaoEncontradaException e){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, e.getMessage() );
        }
    }

    /**
     *  Anotação DeleteMapping para pegar o parâmeto passado na url, informar que metodo é um Delete
     *  Anotação ResponseStatus(HttpStatus.NO_CONTENT) para ter o retorno 204 sucesso no NO_CONTENT
     */
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Pauta pautaAtualizado){
        try {
            service.atualizar( id, pautaAtualizado );
        }catch (PautaNaoEncontradaException e){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, e.getMessage() );
        }

    }
}
