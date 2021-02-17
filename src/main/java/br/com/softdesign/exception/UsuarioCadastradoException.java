package br.com.softdesign.exception;

/**
 * @author Junior
 * @since 16/02/2021
 * @version 1.0
 *
 * Classe criada para implementar uma exception especifica para o usuario
 *
 */

public class UsuarioCadastradoException extends RuntimeException{

    public UsuarioCadastradoException(String login){
        super("Usuário já cadastrado para o login "+ login);
    }
}
