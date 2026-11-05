package com.hardy.grocery.cart.application.service;

import com.hardy.grocery.cart.domain.entity.Cart;
import com.hardy.grocery.cart.domain.entity.CartItem;
import com.hardy.grocery.cart.infrastructure.repository.CartRepository;
import com.hardy.grocery.infra.exception.InvalidQuantityException;
import com.hardy.grocery.product.application.service.ProductService;
import com.hardy.grocery.product.domain.entity.Product;

/**
 * Serviço responsável pelas regras de negócio do Carrinho de Compras.
 */
public class CartService {

    /**
     * Repositório para guardar os carrinhos.
     */
    private final CartRepository cartRepository;

    /**
     * Serviço de produtos, usado para validar se há stock.
     */
    private final ProductService productService;

    /**
     * Construtor que injeta as dependências.
     * 
     * @param cartRepository O repositório do carrinho.
     * @param productService O serviço de produtos.
     */
    public CartService(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    /**
     * Cria um novo carrinho vazio se ele ainda não existir.
     * 
     * @param cartId O ID do novo carrinho.
     * @return O carrinho criado ou o existente.
     */
    public Cart createOrGetCart(String cartId) {
        return cartRepository.findById(cartId).orElseGet(() -> {
            Cart newCart = new Cart(cartId);
            cartRepository.save(newCart);
            return newCart;
        });
    }

    /**
     * Adiciona um produto ao carrinho, validando stock e quantidade.
     * 
     * @param cartId    O ID do carrinho.
     * @param productId O ID do produto a adicionar.
     * @param quantity  A quantidade desejada.
     * @return O carrinho atualizado.
     */
    public Cart addItemToCart(String cartId, String productId, int quantity) {
        if (quantity <= 0) {
            throw new InvalidQuantityException("Quantity to add must be greater than zero.");
        }

        Cart cart = createOrGetCart(cartId);
        Product product = productService.getProductById(productId);

        if (product.getStock() < quantity) {
            throw new InvalidQuantityException("Not enough stock for product: " + product.getName() +
                    ". Available: " + product.getStock());
        }

        String cartItemId = cart.getId() + "-" + product.getId();
        CartItem cartItem = new CartItem(cartItemId, product, quantity);
        cart.addItem(cartItem);
        cartRepository.save(cart);

        return cart;
    }

    /**
     * Retorna um carrinho existente pelo seu ID.
     * 
     * @param cartId O ID do carrinho.
     * @return O carrinho encontrado.
     */
    public Cart getCart(String cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found with ID: " + cartId));
    }

    /**
     * Remove um produto específico do carrinho.
     * 
     * @param cartId    O ID do carrinho.
     * @param productId O ID do produto a remover.
     * @return O carrinho atualizado.
     */
    public Cart removeItemFromCart(String cartId, String productId) {
        Cart cart = getCart(cartId);
        cart.removeItem(productId);
        cartRepository.save(cart);
        return cart;
    }
}