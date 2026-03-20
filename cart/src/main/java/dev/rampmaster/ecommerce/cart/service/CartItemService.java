package dev.rampmaster.ecommerce.cart.service;

import dev.rampmaster.ecommerce.cart.client.ProductClient;
import dev.rampmaster.ecommerce.cart.client.UserClient;
import dev.rampmaster.ecommerce.cart.model.CartItem;
import dev.rampmaster.ecommerce.cart.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    private final CartItemRepository repository;
    private final ProductClient productClient;
    private final UserClient userClient;

    public CartItemService(CartItemRepository repository, ProductClient productClient, UserClient userClient) {
        this.repository = repository;
        this.productClient = productClient;
        this.userClient = userClient;
    }

    public List<CartItem> findAll() {
        var cartItems = repository.findAll();
        cartItems.forEach(this::enrichCartItem);
        return cartItems;
    }

    public Optional<CartItem> findById(Long id) {
        return repository.findById(id).map(this::enrichCartItem);
    }

    public CartItem create(CartItem entity) {
        entity.setId(null);
        return repository.save(entity);
    }

    public Optional<CartItem> update(Long id, CartItem entity) {
        if (!repository.existsById(id)) {
            return Optional.empty();
        }
        entity.setId(id);
        return Optional.of(repository.save(entity));
    }

    public List<CartItem> findbyUserId(long userId){
        var cartItems = repository.findByUserId(userId);
        cartItems.forEach(this::enrichCartItem);
        return cartItems;
    }

    public boolean delete(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    private CartItem enrichCartItem(CartItem cartItem) {
        // Enrich with Product details
        try {
            var product = productClient.getProductById(cartItem.getProductId());
            cartItem.setProduct(product);
        } catch (Exception e) {
            System.err.println("Error fetching product " + cartItem.getProductId() + ": " + e.getMessage());
        }

        // Enrich with User details
        try {
            var user = userClient.getUserById(cartItem.getUserId());
            cartItem.setUser(user);
        } catch (Exception e) {
            System.err.println("Error fetching user " + cartItem.getUserId() + ": " + e.getMessage());
        }

        return cartItem;
    }
}
