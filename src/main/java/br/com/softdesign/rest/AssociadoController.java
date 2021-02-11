package br.com.softdesign.rest;

import br.com.softdesign.model.entity.Associado;
import br.com.softdesign.model.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    /*Anotação para informar que metodo é um post*/
    @PostMapping
    /*Anotação para ter o retorno da requisição para o client*/
    @ResponseStatus(HttpStatus.CREATED)
    public Associado salvar( @RequestBody Associado associado ){
        /*Anotação RequestBody indica que o objeto JSON será passado no corpo da requisição*/
        return repository.save(associado);
    }

    /*Anotação para pegar o parâmeto id passado na url*/
    @GetMapping("{id}")
    public Associado acharPorId( @PathVariable Integer id){
        return repository
                .findById(id)
                .orElseThrow(()-> new ResponseStatusException((HttpStatus.NOT_FOUND)));
                /*Se não encontrar o cliente por id será lançado o erro que objeto não foi encontrado*/
    }
}
