package com.kassiazama.bigchatbrasil.exception;

public class MethodNotAllowedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MethodNotAllowedException(String mensagem) {
        super(mensagem);
    }
    
}