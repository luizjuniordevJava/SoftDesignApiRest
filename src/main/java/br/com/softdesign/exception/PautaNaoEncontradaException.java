package br.com.softdesign.exception;
/**
 * @author Junior
 * @since 18/02/2021
 * @version 1.0
 *
 * Classe criada para implementar uma exception especifica "Pauta não encontrada!"
 *
 */
public class PautaNaoEncontradaException extends RuntimeException{
   public PautaNaoEncontradaException() {
       super("Pauta não encontrada!");
   }

}
