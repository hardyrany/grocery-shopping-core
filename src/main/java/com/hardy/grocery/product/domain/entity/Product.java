package com.hardy.grocery.product.domain.entity;

/*
 * # Definição (Entity)
 * - Uma Entity é um objeto de domínio que possui uma identidade única (ID).
 * - Representa os dados e as regras de negócio fundamentais do sistema.
 * - Vive sempre na pasta `domain/entity/` de cada módulo.
 */

public class Product {

    /**
     * Identificador único do produto (ex: "PROD-001").
     * É a identidade da Entity. Não pode ser alterado após a criação.
     */
    private String id;

    /**
     * Nome descritivo do produto para exibição (ex: "Arroz Agulha").
     */
    private String name;

    /**
     * Preço unitário do produto. Usamos double por ser um projeto simples.
     * (Em produção, usar-se-ia BigDecimal para precisão financeira).
     */
    private double price;

    /**
     * Quantidade física disponível no armazém. É um valor inteiro (não há frações
     * de itens).
     */
    private int stock;

    /**
     * Construtor vazio (No-args).
     * Obrigatório para frameworks (Spring/JPA) e serialização (JSON), que precisam
     * de instanciar o objeto antes de preencher os dados.
     */
    public Product() {
    }

    /**
     * Construtor com parâmetros.
     * Obriga a fornecer todos os dados essenciais, garantindo que a entidade
     * nunca é criada num estado inválido.
     */
    public Product(String id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Getter do ID.
     * Permite ler o identificador do produto.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter do ID.
     * Permite alterar o identificador (raro, mas necessário para frameworks).
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter do Nome.
     * Permite ler o nome do produto.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter do Nome.
     * Permite alterar o nome do produto.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter do Preço.
     * Permite ler o preço unitário do produto.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter do Preço.
     * Permite alterar o preço unitário do produto.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter do Stock.
     * Permite ler a quantidade atual em armazém.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Setter do Stock.
     * Permite atualizar a quantidade em armazém.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Método de negócio: sell.
     * Verifica se há stock suficiente para a quantidade pedida.
     * Se sim, subtrai ao stock e retorna true.
     * Se não, retorna false (não altera o stock).
     */
    public boolean sell(int amount) {
        if (amount <= 0 || amount > stock) {
            return false;
        }
        this.stock -= amount;
        return true;
    }

    /**
     * Método de negócio: addStock.
     * Aumenta a quantidade em stock, desde que o valor passado seja maior que zero.
     */
    public void addStock(int amount) {
        if (amount > 0) {
            this.stock += amount;
        }
    }

    /**
     * toString.
     * Retorna uma representação em texto do objeto Product, útil para debug e
     * impressão na consola.
     */
    @Override
    public String toString() {
        return String.format("Product{id='%s', name='%s', price=%.2f, stock=%d}",
                id, name, price, stock);
    }
}