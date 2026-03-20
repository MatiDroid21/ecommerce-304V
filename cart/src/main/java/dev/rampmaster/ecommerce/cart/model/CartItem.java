package dev.rampmaster.ecommerce.cart.model;

import dev.rampmaster.ecommerce.cart.DTO.ProductDTO;
import dev.rampmaster.ecommerce.cart.DTO.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "cart_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long productId;
    private Integer quantity;

    @Transient
    private ProductDTO product;

    @Transient
    private UserDTO user;

    @Builder.Default
    private String status = "PENDING";

    @Column(name="create_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if(this.status == null){
            this.status = "PENDING";
        }
    }
}
