package dev.rampmaster.ecommerce.cart.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class cartOpenApiConfig {

    @Bean
    @Primary
    public OpenAPI cartOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cart API")
                        .description("Documentacion del microservicio de carrito de compras")
                        .version("v1"))
                .addServersItem(new Server()
                        .url("/cart")
                        .description("Context path del microservicio cart"));
    }
}

