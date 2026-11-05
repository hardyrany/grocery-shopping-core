package com.hardy.grocery.infra.exception;

/**
 * Exceção lançada quando um produto procurado não é encontrado no sistema.
 */
public class ProductNotFoundException extends RuntimeException {
    
    /**
     * Construtor que recebe o ID do produto não encontrado e formata a mensagem.
     * @param id O ID do produto que falhou a busca.
     */
    public ProductNotFoundException(String id) {
        super("Product not found with ID: " + id);
    }
}