package br.com.softdesign.rest;

import br.com.softdesign.model.entity.Associado;
import br.com.softdesign.model.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    public AssociadoController(AssociadoRepository repository){
        this.repository = repository;
    }


    @PostMapping
    /*Anotação para ter o retorno da requisição*/
    @ResponseStatus(HttpStatus.CREATED)
    public Associado salvar( @RequestBody //Anotação para indicar que o objeto será passado no corpo da requisição
                    Associado associado ){
        return repository.save(associado);
    }
}
