package dev.rampmaster.ecommerce.cart.service;

public interface NotificationService {
    void enviarRecordatorioCarrito(String email, String username);
}