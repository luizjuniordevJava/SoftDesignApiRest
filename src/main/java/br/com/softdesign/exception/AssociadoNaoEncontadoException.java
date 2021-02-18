package br.com.softdesign.exception;
/**
 * @author Junior
 * @since 18/02/2021
 * @version 1.0
 *
 * Classe criada para implementar uma exception especifica "Associado não encontrado!"
 *
 */
public class AssociadoNaoEncontadoException extends RuntimeException {
    public AssociadoNaoEncontadoException(){
        super("Associado não encontrado!");
    }
}
