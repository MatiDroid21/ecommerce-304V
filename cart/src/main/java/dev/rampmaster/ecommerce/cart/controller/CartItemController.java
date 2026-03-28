package dev.rampmaster.ecommerce.cart.controller;

import dev.rampmaster.ecommerce.cart.DTO.CouponRequestDTO;
import dev.rampmaster.ecommerce.cart.model.CartItem;
import dev.rampmaster.ecommerce.cart.service.CartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartItemController {

    private final CartItemService service;

    public CartItemController(CartItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<CartItem> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItem> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<CartItem> findByUserId(@PathVariable Long userId) {
        return service.findbyUserId(userId);
    }

    @PostMapping
    public ResponseEntity<CartItem> create(@RequestBody CartItem entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItem> update(@PathVariable Long id, @RequestBody CartItem entity) {
        return service.update(id, entity)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!service.delete(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    //Cupon de descuento
    //#NOTA CORREGIR Y PREGUNTAR SI ERA AQUIEN LE PERTENECIA EL CARRITO O QUE PRODUCTOS LLEVA EN ELCARRITO AL PROFE
    //#MEJORA A FUTURO: AGREGAR EL DESCUENTO APLICADO A CADA PRODUCTO DEL CARRITO
    //#MEJORA: MOSTRAR EL % DE DESCUENTO , AUNQE LO DICE EN EL CODIGO DE DSCT
    @PostMapping("/user/{userId}/apply-coupon")
    public ResponseEntity<List<CartItem>> applyCoupon(@PathVariable Long userId, @RequestBody CouponRequestDTO request) {
        try {
            List<CartItem> discountedCart = service.applyCoupon(userId, request.getCouponCode());
            return ResponseEntity.ok(discountedCart);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalError(Exception ex) {
        return ResponseEntity.badRequest().build();
    }
}
