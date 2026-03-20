package dev.rampmaster.ecommerce.users.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Genera Getters, Setters, toString, equals y hashCode automáticamente
@NoArgsConstructor // Constructor vacío para JPA
@AllArgsConstructor // Constructor con todos los campos
@Builder // Para crear usuarios fácil: UserAccount.builder().username("Pitt").build()
public class UserAccount {

    private Long id;
    private String username;
    private String email;
    private String role;

    @Builder.Default
    private boolean active = true; // Por defecto activo
}