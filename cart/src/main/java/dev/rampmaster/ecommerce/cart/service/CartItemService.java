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
        validateItem(entity); // <-Validacion cantidad carrito.
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

    public boolean delete(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    // Validaciones de negocio para la entidad CartItem
    public void validateItem(CartItem entity){
        // El carrito no puede quedar en negativo.
        if(entity.getQuantity() <= 0){
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
    }
}

