package dev.rampmaster.ecommerce.cart.service;

import dev.rampmaster.ecommerce.cart.model.CartItem;
import dev.rampmaster.ecommerce.cart.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    private final CartItemRepository repository;

    public CartItemService(CartItemRepository repository) {
        this.repository = repository;
    }

    public List<CartItem> findAll() {
        return repository.findAll();
    }

    public Optional<CartItem> findById(Long id) {
        return repository.findById(id);
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
        return repository.findByUserId(userId);
    }

    public boolean delete(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}

