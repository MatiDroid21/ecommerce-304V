package dev.rampmaster.ecommerce.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private JavaMailSender mailSender;
    /*
    -JavaMailSender -> Configura una interfaz para utilizar los datos en properties y poder enviar correos.

    */
    @Override
    public void enviarRecordatorioCarrito(String email, String username) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("¡E-Commerce 'Cart': Te espera tu carrito!");
        message.setText("Hola " + username + ",\nVimos que dejaste productos en tu carrito. ¡Vuelve antes de que se agoten!");

        mailSender.send(message);
        System.out.println("LOG: Correo enviado con éxito a " + email);
    }
}