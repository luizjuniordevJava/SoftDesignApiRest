package br.com.softdesign.rest;

import br.com.softdesign.model.entity.Usuario;
import br.com.softdesign.model.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    private final UsuarioRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid Usuario usuario){
        repository.save(usuario);
    }
}
