package com.example.pruebaCrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.pruebaCrud"})
@EnableAspectJAutoProxy
public class PruebaCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaCrudApplication.class, args);
	}

}
