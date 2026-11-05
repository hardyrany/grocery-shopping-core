package com.hardy.grocery.infra.exception;

/**
 * Classe responsável por capturar e formatar as exceções de forma centralizada.
 * Em Pure Java, isto serve para imprimir mensagens de erro amigáveis na consola.
 * Em Spring Boot, esta classe seria anotada com @RestControllerAdvice.
 */
public class GlobalExceptionHandler {

    /**
     * Trata a exceção de produto não encontrado.
     * @param e A exceção capturada.
     */
    public static void handleProductNotFound(ProductNotFoundException e) {
        System.err.println("[ERROR] Product Error: " + e.getMessage());
    }

    /**
     * Trata a exceção de quantidade inválida.
     * @param e A exceção capturada.
     */
    public static void handleInvalidQuantity(InvalidQuantityException e) {
        System.err.println("[ERROR] Quantity Error: " + e.getMessage());
    }

    /**
     * Trata erros genéricos ou inesperados (ex: falha de leitura de ficheiro).
     * @param e A exceção capturada.
     */
    public static void handleGenericException(Exception e) {
        System.err.println("[ERROR] Unexpected Error: " + e.getMessage());
    }
}