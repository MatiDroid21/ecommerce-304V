package dev.rampmaster.ecommerce.cart.repository;

import dev.rampmaster.ecommerce.cart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Modificacion de clase a interface, para poder utilizar H2
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}