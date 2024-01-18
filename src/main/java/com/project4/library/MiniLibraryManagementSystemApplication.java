package com.project4.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MiniLibraryManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniLibraryManagementSystemApplication.class, args);
	}

}
