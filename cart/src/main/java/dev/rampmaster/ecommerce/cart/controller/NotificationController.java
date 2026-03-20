package dev.rampmaster.ecommerce.cart.controller;

import dev.rampmaster.ecommerce.cart.DTO.ApiResponse;
import dev.rampmaster.ecommerce.cart.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // Endpoint para probar el envío manual
    @PostMapping("/test-email")
    public ResponseEntity<ApiResponse> enviarPrueba(@RequestParam String email, @RequestParam String nombre) {
        try {
            notificationService.enviarRecordatorioCarrito(email, nombre);
            return ResponseEntity.ok(new ApiResponse(true, "Correo enviado con éxito a " + email));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Error al enviar: " + e.getMessage()));
        }
    }
}