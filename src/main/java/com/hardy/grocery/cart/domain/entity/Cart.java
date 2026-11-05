package com.hardy.grocery.cart.domain.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidade que representa o Carrinho de Compras (Aggregate Root).
 * Gere a lista de itens e calcula o valor total da compra.
 */
public class Cart {

    /**
     * Identificador único do carrinho.
     */
    private String id;

    /**
     * Lista de itens adicionados ao carrinho.
     */
    private List<CartItem> items;

    /**
     * Construtor vazio (No-args).
     * Inicializa a lista de itens para evitar NullPointerException.
     */
    public Cart() {
        this.items = new ArrayList<>();
    }

    /**
     * Construtor com parâmetros.
     * @param id O ID do carrinho.
     */
    public Cart(String id) {
        this.id = id;
        this.items = new ArrayList<>();
    }

    /**
     * Retorna o ID do carrinho.
     */
    public String getId() {
        return id;
    }

    /**
     * Define o ID do carrinho.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retorna a lista atual de itens no carrinho.
     */
    public List<CartItem> getItems() {
        return items;
    }

    /**
     * Adiciona um item ao carrinho.
     * @param item O item a ser adicionado.
     */
    public void addItem(CartItem item) {
        if (item != null) {
            this.items.add(item);
        }
    }

    /**
     * Remove um item do carrinho pelo ID do produto.
     * @param productId O ID do produto a remover.
     */
    public void removeItem(String productId) {
        this.items.removeIf(item -> item.getProduct().getId().equals(productId));
    }

    /**
     * Calcula o valor total do carrinho somando os subtotais.
     * @return O valor total da compra.
     */
    public double getTotal() {
        double total = 0.0;
        for (CartItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    /**
     * Representação em texto do carrinho.
     */
    @Override
    public String toString() {
        return String.format("Cart{id='%s', itemsCount=%d, total=%.2f}",
                id, items.size(), getTotal());
    }
}