package dev.rampmaster.ecommerce.cart.tasks;

import dev.rampmaster.ecommerce.cart.model.CartItem;
import dev.rampmaster.ecommerce.cart.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class CartReminderTask {

    @Autowired
    private CartItemRepository cartItemRepository;

    //Documentacion: https://docs.spring.io/spring-framework/reference/integration/scheduling.html
    //Scheduled automatización para carritos en abandono.
    //@Scheduled(cron = "0 0 * * * *") -> Seg,Min,Hrs,Dia,Mes,Año
    @Scheduled(fixedRate = 10000) // -> Cada 10 segundos
    public void revisarCarritosAbandonados(){
        LocalDateTime limite = LocalDateTime.now().plusMinutes(5);

        List<CartItem> abandonados = cartItemRepository.findByStatusAndCreatedAtBefore("PENDING",limite);

        //Realizar mejora enviando emails al usuario.
        if(!abandonados.isEmpty()){
            System.out.println("LOG: Se encontraron " + abandonados.size() + " carritos abandonados");
        }
    }

}
