package com.hardy.grocery.product.application.service;

import java.util.List;

import com.hardy.grocery.infra.exception.InvalidQuantityException;
import com.hardy.grocery.infra.exception.ProductNotFoundException;
import com.hardy.grocery.product.domain.entity.Product;
import com.hardy.grocery.product.infrastructure.repository.ProductRepository;


/**
 * Serviço responsável pelas regras de negócio relacionadas aos Produtos.
 * Ele orquestra as chamadas ao repositório e aplica validações.
 */
public class ProductService {

    /**
     * O repositório é injetado via construtor.
     * Isso permite que o serviço não saiba COMO os dados são guardados (se em memória, 
     * base de dados ou ficheiro), apenas que ele PODE guardá-los.
     */
    private final ProductRepository repository;

    /**
     * Construtor que injeta a dependência do repositório.
     * @param repository A implementação do repositório de produtos.
     */
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    /**
     * Regista um novo produto no sistema.
     * Valida se os dados estão corretos antes de guardar.
     * 
     * @param id O ID do produto.
     * @param name O nome do produto.
     * @param price O preço unitário.
     * @param stock A quantidade inicial.
     * @return O produto criado.
     * @throws InvalidQuantityException Se o preço ou o stock forem negativos.
     * @throws IllegalArgumentException Se o produto já existir.
     */
    public Product createProduct(String id, String name, double price, int stock) {
        // Regra de negócio: Não permitir preços ou stocks negativos
        if (price < 0) {
            throw new InvalidQuantityException("Price cannot be negative.");
        }
        if (stock < 0) {
            throw new InvalidQuantityException("Stock cannot be negative.");
        }

        // Regra de negócio: Não permitir IDs duplicados
        if (repository.findById(id).isPresent()) {
            throw new IllegalArgumentException("Product with ID " + id + " already exists.");
        }

        Product newProduct = new Product(id, name, price, stock);
        repository.save(newProduct);
        return newProduct;
    }

    /**
     * Procura um produto pelo seu ID.
     * 
     * @param id O ID do produto.
     * @return O produto encontrado.
     * @throws ProductNotFoundException Se o produto não for encontrado.
     */
    public Product getProductById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    /**
     * Retorna a lista de todos os produtos disponíveis.
     * 
     * @return Uma lista de produtos.
     */
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    /**
     * Remove um produto do sistema.
     * 
     * @param id O ID do produto a ser removido.
     * @throws ProductNotFoundException Se o produto não existir.
     */
    public void deleteProduct(String id) {
        // Garante que o produto existe antes de o tentar apagar
        if (repository.findById(id).isEmpty()) {
            throw new ProductNotFoundException(id);
        }
        repository.deleteById(id);
    }

    /**
     * Adiciona stock a um produto existente.
     * 
     * @param id O ID do produto.
     * @param amount A quantidade a ser adicionada.
     * @throws ProductNotFoundException Se o produto não existir.
     * @throws InvalidQuantityException Se a quantidade for menor ou igual a zero.
     */
    public void addStock(String id, int amount) {
        if (amount <= 0) {
            throw new InvalidQuantityException("Amount to add must be greater than zero.");
        }

        Product product = getProductById(id); // Reutiliza a lógica de busca
        product.addStock(amount);
        repository.save(product); // Guarda o estado atualizado
    }
}