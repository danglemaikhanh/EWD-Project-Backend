package com.dlmk.cheflist_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CheflistBackendApplication {

	public static void main(String[] args) {
        SpringApplication.run(CheflistBackendApplication.class, args);
	}

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

}
