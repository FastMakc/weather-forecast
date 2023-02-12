package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "${service.title}", description = "This service bla bla bla",
		contact = @Contact(name = "OOO Vasya Pupkin", url = "www.bla.com/support", email = "support@vasya.com"), version = "1.0.0"),
		servers = {
				@Server(url = "test.env", description = "test environment"),
				@Server(url ="http://localhost:8081", description = "localhost"),
				@Server(url = "prod.env", description = "production environment"),
		}
)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
