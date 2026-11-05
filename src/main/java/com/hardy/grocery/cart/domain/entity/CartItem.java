package com.hardy.grocery.cart.domain.entity;

import com.hardy.grocery.product.domain.entity.Product;

/**
 * Entidade que representa um item dentro do carrinho de compras.
 * Associa um Produto a uma quantidade escolhida pelo utilizador.
 */
public class CartItem {

    /**
     * Identificador único do item no carrinho (ex: "CART-01-PROD-01").
     */
    private String id;

    /**
     * O produto que está a ser adicionado ao carrinho.
     */
    private Product product;

    /**
     * A quantidade deste produto que o utilizador pretende comprar.
     */
    private int quantity;

    /**
     * Construtor vazio (No-args).
     * Necessário para frameworks e serialização.
     */
    public CartItem() {
    }

    /**
     * Construtor com parâmetros.
     * @param id O ID do item.
     * @param product O produto associado.
     * @param quantity A quantidade escolhida.
     */
    public CartItem(String id, Product product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Retorna o ID do item.
     */
    public String getId() {
        return id;
    }

    /**
     * Define o ID do item.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retorna o produto associado a este item.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Define o produto associado.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Retorna a quantidade do produto.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Define a quantidade do produto.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Calcula o subtotal deste item (Preço do Produto * Quantidade).
     * @return O valor total deste item.
     */
    public double getSubtotal() {
        if (product != null) {
            return product.getPrice() * quantity;
        }
        return 0.0;
    }

    /**
     * Representação em texto do item do carrinho.
     */
    @Override
    public String toString() {
        return String.format("CartItem{id='%s', product='%s', quantity=%d, subtotal=%.2f}",
                id, (product != null ? product.getName() : "null"), quantity, getSubtotal());
    }
}