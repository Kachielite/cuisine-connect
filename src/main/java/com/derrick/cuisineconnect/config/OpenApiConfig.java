package com.derrick.cuisineconnect.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;


@OpenAPIDefinition(
        info = @Info(
            contact = @Contact(
                    name = "Derrick Madumere",
                    email = "derrick.onyekachi@gmail.com",
                    url = "https://derrick-madumere.com"
            ),
                description = "Cusinine Connect API Documentation",
                title = "Cusinine Connect",
                version = "1.0.0"
        ),
        servers = {
                @Server(
                        description = "Local environment",
                        url = "http://localhost:8080/api/v1"
                )
        }
)
public class OpenApiConfig {
}
