package com.clinic.vaxschedular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import com.clinic.vaxschedular.Entity.Admin;
import com.clinic.vaxschedular.Repository.AdminRepo;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
// (exclude = SecurityAutoConfiguration.class)
public class VaxschedularApplication {

	@Autowired
	private AdminRepo adminRepo;

	public static void main(String[] args) {
		SpringApplication.run(VaxschedularApplication.class, args);

	}

	@PostConstruct
	public void Add_Admin() {
		Admin admin = new Admin(1,
				200,
				"First",
				"Admin",
				"Admin@Example.com",
				"Admin123",
				null);
		adminRepo.save(admin);
	}
}
