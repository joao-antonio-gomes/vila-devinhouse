package br.com.village;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;

@SpringBootApplication
public class VillageApplication {

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(VillageApplication.class, args);
	}

}
