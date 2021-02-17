package br.com.softdesign.exception;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * @author Junior
 * @since 11/02/2021
 * @version 1.0
 *
 * Classe criada para padronizar os erros em objeto JSON
 */

public class ApiErrors {

    /*Anotação Lombok para gerar o metodo get em tempo execução*/
    @Getter
    private List<String> errors;

    public ApiErrors(List<String> errors){
        this.errors = errors;
    }

    public ApiErrors(String message){
        this.errors = Arrays.asList(message);
    }
}
