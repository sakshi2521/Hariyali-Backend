package com.hariyali.hariyali_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class HariyaliProjectApplication implements CommandLineRunner{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public static void main(String[] args) {
		SpringApplication.run(HariyaliProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		System.err.println(this.passwordEncoder.encode("admin"));
//		System.out.println(this.passwordEncoder.matches("admin", "$2a$10$vrz46pHtOmo9ULxAzk.EEueY0Q7Hf4I/9.01TrU562vGe1/aDwjkS"));

	}

}
