package com.hardy.grocery.infra.exception;

/**
 * Exceção lançada quando uma operação envolve uma quantidade inválida (ex: zero ou negativa).
 * Estende RuntimeException para ser uma Unchecked Exception (não obriga a try-catch em todo o lado,
 * mas pode ser tratada pelo Handler global).
 */
public class InvalidQuantityException extends RuntimeException {
    
    /**
     * Construtor que recebe uma mensagem de erro personalizada.
     * @param message A descrição do erro.
     */
    public InvalidQuantityException(String message) {
        super(message);
    }
}