package br.com.softdesign.rest;

import br.com.softdesign.exception.UsuarioCadastradoException;
import br.com.softdesign.model.entity.Usuario;
import br.com.softdesign.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

/**
 * @author Junior
 * @since 16/02/2021
 * @version 1.0
 *
 * Classe criada para implementar a controller dos usuarios
 *
 * Anotação para implementar RestController
 * Anotação RequiredArgsConstructor lombok para gerar o construtor como final em tempo de execução
 * Anotação RequestMapping para indicar a rota
 *
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid Usuario usuario){
        try {
            service.salvar(usuario);
        }catch (UsuarioCadastradoException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }

    }
}
