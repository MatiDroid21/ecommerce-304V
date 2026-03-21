package dev.rampmaster.ecommerce.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void enviarRecordatorioCarrito(String email, String username) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("¡Alerta de Carrito Abandonado en E-Commerce 'Cart'!");
        
        String text = String.format(
            "Hola %s,\n\n" +
            "¡Hemos notado que dejaste algunos artículos en tu carrito de compras!\n\n" +
            "Los productos populares se agotan rápido y no queremos que te quedes sin los tuyos.\n\n" +
            "Para completar tu compra, haz clic aquí: http://tu-ecommerce.com/cart\n\n" +
            "¡Gracias por tu interés!\n" +
            "El equipo de E-Commerce 'Cart'",
            username
        );
        
        message.setText(text);

        mailSender.send(message);
        System.out.println("LOG: Correo de alerta de carrito abandonado enviado con éxito a " + email);
    }
}
