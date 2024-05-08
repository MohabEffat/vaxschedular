package com.clinic.vaxschedular;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VaxschedularApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaxschedularApplication.class, args);
	}

	// @PostConstruct
	// public void Add_Admin() {
	// Admin admin = new Admin(1,
	// 200,
	// "First",
	// "Admin",
	// "Admin@Example.com",
	// "Admin123",
	// null, null);
	// adminRepo.save(admin);
	// }
}
