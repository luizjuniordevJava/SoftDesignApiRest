package br.com.softdesign.rest;

import br.com.softdesign.model.entity.Pauta;
import br.com.softdesign.model.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
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
    private final PautaRepository repository;

    @GetMapping
    public List<Pauta> obterTodos(){

        return repository.findAll();
    }

    /**
     *  Anotação para informar que metodo é um post
     *  Anotação para ter o retorno da requisição para o client
     *  Anotação RequestBody indica que o objeto JSON será passado no corpo da requisição
     *  Anotação Valid para validar o objeto pauta
     *  Anotação ResponseStatus(HttpStatus.CREATED) retorno de msg pro client
     *
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pauta salvar( @RequestBody @Valid Pauta pauta ){
        return repository.save(pauta);
    }

    /**
     *  Anotação GetMapping para pegar o parâmeto passado na url
     *  findById primeiro busca a pauta
     *  orElseThrow se não encontrar a pauta por id será lançado o erro que objeto não foi encontrado
     */
    @GetMapping("{id}")
    public Pauta acharPorId( @PathVariable Integer id){
        return repository
                .findById(id)
                .orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"pauta não encontrado!"));
    }

    /**
     *  Anotação DeleteMapping para pegar o parâmeto passado na url, informar que metodo é um Delete
     *  Anotação ResponseStatus(HttpStatus.NO_CONTENT) para ter o retorno 204 sucesso no NO_CONTENT
     *  findById primeiro busca a pauta
     *  orElseThrow se não encontrar a pauta por id será lançado o erro que objeto não foi encontrado
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar( @PathVariable Integer id){
        repository
                .findById(id)
                .map( pauta -> {
                    repository.delete(pauta);
                    return Void.TYPE;
                })
                .orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"pauta não encontrado!"));
    }

    /**
     *  Anotação DeleteMapping para pegar o parâmeto passado na url, informar que metodo é um Delete
     *  Anotação ResponseStatus(HttpStatus.NO_CONTENT) para ter o retorno 204 sucesso no NO_CONTENT
     *  findById primeiro busca a pauta
     *  orElseThrow se não encontrar a pauta por id será lançado o erro que objeto não foi encontrado
     */
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Pauta pautaAtualizado){
        repository
                .findById(id)
                .map( pauta -> {
                    pautaAtualizado.setId(pauta.getId());
                    return repository.save(pautaAtualizado);
                })
                .orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"pauta não encontrado!"));
    }
}
