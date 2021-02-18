package br.com.softdesign.exception;
/**
 * @author Junior
 * @since 18/02/2021
 * @version 1.0
 *
 * Classe criada para implementar uma exception especifica "Associado já possuí voto para esta pauta!"
 *
 */
public class VotoRegistradoException extends RuntimeException {
    public VotoRegistradoException(){
        super("Associado já possuí voto para esta pauta!");
    }
}
