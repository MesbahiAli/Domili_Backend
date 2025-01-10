package com.example.domily;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Domily API",
        version = "1.0",
        description = "API documentation for the Domily project"
    )
)
public class DomilyApplication {
    public static void main(String[] args) {
        SpringApplication.run(DomilyApplication.class, args);
    }
}
