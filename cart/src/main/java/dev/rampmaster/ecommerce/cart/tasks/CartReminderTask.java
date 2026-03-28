package dev.rampmaster.ecommerce.cart.tasks;

import dev.rampmaster.ecommerce.cart.client.UserClient;
import dev.rampmaster.ecommerce.cart.model.CartItem;
import dev.rampmaster.ecommerce.cart.repository.CartItemRepository;
import dev.rampmaster.ecommerce.cart.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class CartReminderTask {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private NotificationService notificationService;

    @Scheduled(fixedRate = 60000) // Cada minuto para probar
    public void revisarYNotificar() {
        LocalDateTime limite = LocalDateTime.now().minusMinutes(1);
        List<CartItem> abandonados = cartItemRepository.findByStatusAndCreatedAtBefore("PENDING", limite);

        for (CartItem item : abandonados) {
            try {
                var user = userClient.getUserById(item.getUserId());
                if (user != null) {
                    notificationService.enviarRecordatorioCarrito(user.getEmail(), user.getUsername());

                    // IMPORTANTE: Cambia el estado para que no le mande 100 correos!!
                    item.setStatus("REMINDED");
                    cartItemRepository.save(item);
                }
            } catch (Exception e) {

                System.err.println("Error fetching user " + item.getUserId() + ": " + e.getMessage());
            }
        }
    }
}
