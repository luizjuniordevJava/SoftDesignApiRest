package br.com.softdesign.exception;

public class CPFCadastradoException extends RuntimeException {

    public CPFCadastradoException (){
        super("CPF já cadastrado");
    }
}
