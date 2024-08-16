package com.github.aliftrd.sei;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SeiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeiApplication.class, args);
	}

	@Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}
