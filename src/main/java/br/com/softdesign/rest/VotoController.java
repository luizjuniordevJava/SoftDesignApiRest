package br.com.softdesign.rest;

import br.com.softdesign.exception.VotoRegistradoException;
import br.com.softdesign.service.VotoService;
import br.com.softdesign.service.dto.VotoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

/**
 * @author Junior
 * @since 18/02/2021
 * @version 1.0
 *
 * Classe criada para implementar a controller dos votos
 *
 * Anotação para implementar RestController
 * Anotação RequiredArgsConstructor lombok para gerar o construtor como final em tempo de execução
 * Anotação RequestMapping para indicar a rota
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/votos")
public class VotoController {

    private final VotoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar( @RequestBody @Valid VotoDTO voto ){
        try {
            service.salvar(voto);
        }catch (VotoRegistradoException e){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
