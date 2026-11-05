package com.hardy.grocery.cart.infrastructure.repository;

import com.hardy.grocery.cart.domain.entity.Cart;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Repositório responsável por simular a persistência de dados dos Carrinhos.
 */
public class CartRepository {

    /**
     * Mapa que simula a tabela da base de dados (CartID -> Carrinho).
     */
    private final Map<String, Cart> database = new HashMap<>();

    /**
     * Guarda um carrinho na "base de dados".
     * @param cart O carrinho a ser guardado.
     */
    public void save(Cart cart) {
        database.put(cart.getId(), cart);
    }

    /**
     * Procura um carrinho pelo seu ID.
     * @param id O ID do carrinho.
     * @return Um Optional contendo o carrinho, ou vazio se não existir.
     */
    public Optional<Cart> findById(String id) {
        return Optional.ofNullable(database.get(id));
    }
}