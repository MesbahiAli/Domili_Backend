package com.vaucher.app.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.vaucher.shared.utility.VaucherStartup;

@SpringBootApplication
@EntityScan(basePackages = "com.vaucher")
@EnableJpaRepositories(basePackages = "com.vaucher")
@ComponentScan(basePackages = "com.vaucher")
@EnableConfigurationProperties
public class AppVaucherByTokenBoot implements CommandLineRunner{

	@Autowired
	protected VaucherStartup startup;
	
	public static void main(String[] args) {
		
		System.out.println("AppVaucherByTokenBoot=====>1.0");
		SpringApplication.run(AppVaucherByTokenBoot.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
				
		startup.init();
		
	}
}
