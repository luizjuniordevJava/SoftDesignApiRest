package br.com.softdesign.rest;

import br.com.softdesign.model.entity.Pauta;
import br.com.softdesign.model.repository.PautaRepository;
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
 */

/*Anotação para implementar RestController*/
@RestController
/*Anotação para indicar a rota*/
@RequestMapping("/api/pautas")
@CrossOrigin("http://localhost:4200")
public class PautaController {

    private final PautaRepository repository;

    /*Anotação para permitir a injeção do repository*/
    @Autowired
    public PautaController(PautaRepository repository){

        this.repository = repository;
    }

    @GetMapping
    public List<Pauta> obterTodos(){
        return repository.findAll();
    }

    /*Anotação para informar que metodo é um post*/
    @PostMapping
    /*Anotação para ter o retorno da requisição para o client*/
    @ResponseStatus(HttpStatus.CREATED)
    public Pauta salvar( @RequestBody @Valid Pauta pauta ){
        /*Anotação RequestBody indica que o objeto JSON será passado no corpo da requisição*/
        return repository.save(pauta);
    }

    /*Anotação para pegar o parâmeto passado na url, informar que metodo é um GET*/
    @GetMapping("{id}")
    public Pauta acharPorId( @PathVariable Integer id){
        return repository
                .findById(id)
                .orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"pauta não encontrado!"));
        /*Se não encontrar o cliente por id será lançado o erro que objeto não foi encontrado*/
    }

    /*Anotação para pegar o parâmeto passado na url, informar que metodo é um Delete*/
    @DeleteMapping("{id}")
    /*Anotação para ter o retorno 204 sucesso no NO_CONTENT*/
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar( @PathVariable Integer id){
        repository
                .findById(id) /*primeiro busca a pauta antes de deletar*/
                .map( pauta -> {
                    repository.delete(pauta);
                    return Void.TYPE;
                })
                .orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"pauta não encontrado!"));
        /*Se não encontrar o cliente por id será lançado o erro que objeto não foi encontrado*/
    }

    /*Anotação para pegar o parâmeto passado na url, informar que metodo é um Put(update)*/
    @PutMapping("{id}")
    /*Anotação para ter o retorno 204 sucesso no NO_CONTENT*/
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Pauta pautaAtualizado){
        repository
                .findById(id) /*primeiro busca o pauta antes de deletar*/
                .map( pauta -> {
                    pautaAtualizado.setId(pauta.getId());
                    return repository.save(pautaAtualizado);
                })
                .orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"pauta não encontrado!"));
        /*Se não encontrar o cliente por id será lançado o erro que objeto não foi encontrado*/
    }
}
