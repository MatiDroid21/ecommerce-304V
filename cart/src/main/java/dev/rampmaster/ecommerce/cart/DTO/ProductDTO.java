package dev.rampmaster.ecommerce.cart.DTO;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String category;
    private Long price;
    private boolean active;
    private Long discountedPrice;
}
