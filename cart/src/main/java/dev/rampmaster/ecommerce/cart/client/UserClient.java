package dev.rampmaster.ecommerce.cart.client;

import dev.rampmaster.ecommerce.cart.DTO.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "users", url = "${user.service.url}")
public interface UserClient {

    @GetMapping("/users/api/users/{id}")
    UserDTO getUserById(@PathVariable("id") Long id);
}
