package dev.rampmaster.ecommerce.cart.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_items")
@Data // Agrupación de anotaciones: Getters & Setters
@NoArgsConstructor // Genera el constructor vacío (obligatorio para JPA)
@AllArgsConstructor // Genera el constructor con todos los campos
@Builder // Te permite crear objetos de forma más legible
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long productId;
    private Integer quantity;
}

