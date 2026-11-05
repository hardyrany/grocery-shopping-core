package com.hardy.grocery.product.infrastructure.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.hardy.grocery.product.domain.entity.Product;

/**
 * Repositório responsável por simular a persistência de dados dos Produtos.
 * Em Pure Java, usamos um HashMap em memória.
 * No Spring Boot, esta classe será substituída por uma interface que estende JpaRepository.
 */
public class ProductRepository {

    /**
     * Mapa que simula a tabela da base de dados (ID -> Produto).
     */
    private final Map<String, Product> database = new HashMap<>();

    /**
     * Guarda um produto na "base de dados".
     * @param product O produto a ser guardado.
     */
    public void save(Product product) {
        database.put(product.getId(), product);
    }

    /**
     * Procura um produto pelo seu ID.
     * @param id O ID do produto.
     * @return Um Optional contendo o produto, ou vazio se não existir.
     */
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(database.get(id));
    }

    /**
     * Retorna todos os produtos guardados.
     * @return Uma lista com todos os produtos.
     */
    public List<Product> findAll() {
        return new ArrayList<>(database.values());
    }

    /**
     * Remove um produto da "base de dados" pelo ID.
     * @param id O ID do produto a ser removido.
     */
    public void deleteById(String id) {
        database.remove(id);
    }
}