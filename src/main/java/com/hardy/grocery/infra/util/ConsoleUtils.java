package com.hardy.grocery.infra.util;

import java.util.Scanner;

/**
 * Classe utilitária para centralizar as operações de entrada e saída na consola.
 * Evita que a classe Main fique poluída com lógica de Scanner.
 */
public class ConsoleUtils {

    /**
     * Instância do Scanner para ler input do utilizador (System.in).
     * É estática para ser partilhada e não criar múltiplos Scanners (o que causa bugs).
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Lê uma linha de texto introduzida pelo utilizador.
     * @param prompt A mensagem a mostrar antes de ler.
     * @return A string introduzida.
     */
    public static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /**
     * Lê um número inteiro introduzido pelo utilizador.
     * Inclui um loop de validação: se o utilizador escrever letras, pede novamente.
     * @param prompt A mensagem a mostrar.
     * @return O número inteiro lido.
     */
    public static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    /**
     * Lê um número decimal (double) introduzido pelo utilizador.
     * Substitui a vírgula por ponto para evitar erros em teclados portugueses.
     * @param prompt A mensagem a mostrar.
     * @return O número decimal lido.
     */
    public static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim().replace(",", ".");
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid decimal number.");
            }
        }
    }

    /**
     * Imprime uma linha em branco para espaçamento visual.
     */
    public static void printBlankLine() {
        System.out.println();
    }
}