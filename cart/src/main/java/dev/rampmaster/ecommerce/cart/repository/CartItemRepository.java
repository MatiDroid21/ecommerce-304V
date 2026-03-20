package dev.rampmaster.ecommerce.cart.repository;

import dev.rampmaster.ecommerce.cart.model.CartItem;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Modificacion de clase a interface, para poder utilizar Oracle
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(long userId);

    List<CartItem> findByStatusAndCreatedAtBefore(String status, LocalDateTime date);
}