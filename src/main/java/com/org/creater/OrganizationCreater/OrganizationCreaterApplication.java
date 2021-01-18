package com.org.creater.OrganizationCreater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.org.creater.OrganizationCreater.config.SecurityConfigurer;


@SpringBootApplication
public class OrganizationCreaterApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrganizationCreaterApplication.class, args);
		System.out.println("Statring server.....");
	}

}
